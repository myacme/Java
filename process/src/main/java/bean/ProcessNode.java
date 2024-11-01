package bean;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import service.ProcessHandle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 审批流程节点实体类
 *
 * @author ljx
 * @since 2021-09-24 08:56:52
 */
/*
CREATE TABLE `d_flow_work_node` (
  `configure_id` varchar(32) NOT NULL,
  `node_id` varchar(32) NOT NULL,
  `node_name` varchar(255) DEFAULT NULL,
  `sign` int(1) NOT NULL COMMENT '     节点标识\r\n     0--开始节点\r\n     1--流转节点\r\n     2--结束节点',
  `parent_node_id` varchar(32) DEFAULT NULL,
  `next_node_id` varchar(32) DEFAULT NULL,
  `x` varchar(255) DEFAULT NULL,
  `y` varchar(255) DEFAULT NULL,
  `process_id` varchar(32) NOT NULL COMMENT '运行id',
  `flow_id` varchar(32) NOT NULL,
  `status` int(1) NOT NULL COMMENT '      状态\r\n     0 审批中\r\n     1 已通过\r\n     2 未通过',
  `approval_date` datetime DEFAULT NULL,
  PRIMARY KEY (`process_id`,`flow_id`) USING BTREE,
  KEY `I_configure_id` (`configure_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
@Component
public class ProcessNode extends ConfigureNode {

    public ProcessNode() {
    }

    public ProcessNode(ConfigureNode configureNode, String processId, String flowId) {
        super(configureNode);
        this.processId = processId;
        this.flowId = flowId;
    }

    /**
     * 运行ID
     */
    private String processId;

    /**
     * 流转节点ID
     */
    private String flowId;

    /**
     * 审批标志
     * 0--未审批
     * 1--已审批
     * 2--未通过
     */
    private int status;

    protected List<Approve> approves;

    /**
     * 审批日期
     */
    private Date approvalDate;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Approve> getApproves() {
        return approves;
    }

    public void setApproves(List<Approve> approves) {
        this.approves = approves;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Transactional(rollbackFor = Exception.class)
    public int approve(ProcessHandle mapper) {
        if (!CollectionUtils.isEmpty(this.approves)) {
            for (Approve approve : this.approves) {
                if (approve.getStatus() != 1) {
                    return 0;
                }
            }
        }
        this.status = 1;
        int result = mapper.approveOfNode(this);
        ProcessNode nextNode = mapper.queryNode(this.getNextNodeId());
        if (nextNode.getSign() == 2) {
            nextNode.setStatus(1);
            result += mapper.approveOfNode(nextNode);
            //流程通过
            result += mapper.queryById(this.processId).approve(mapper);
            //启用业务数据
            result += mapper.enableBusiness(this.processId);
            return result;
        }
        List<Approve> approves = nextNode.getApproves();
        if (!CollectionUtils.isEmpty(approves)) {
            for (Approve approve : approves) {
                approve.toDo(mapper);
            }
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int sendBack(ProcessHandle mapper) {
        this.status = 2;
        int result = mapper.approveOfNode(this);
        result += mapper.queryById(this.processId).sendBack(mapper);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int turnDown(ProcessHandle mapper, String businessId) {
        this.status = 3;
        int result = mapper.approveOfNode(this);
        //添加新增节点
        //获取原来的流程节点
        ProcessNode processNode = mapper.queryAllNodesOfProcessTree(this.processId);
        //获取新增的流程节点
        List<ProcessNode> configureNodes = mapper.queryAllNodesOfConfigureTree(this.getConfigureId());
        ProcessNode configureNode = buildTree(configureNodes);
        //添加发起人
        List<Approve> initiators = new ArrayList<>();
        for (Approve initiator : processNode.getApproves()) {
            initiators.add(new Approve(initiator));
        }
        configureNode.setApproves(initiators);
        ProcessNode processNodeTemp = processNode;
        while (true) {
            if (Objects.equals(processNodeTemp.getFlowId(), this.getFlowId())) {
                configureNode.setParentNodeId(processNodeTemp.getFlowId());
                processNodeTemp.setNextNode(configureNode);
                processNodeTemp.setNextNodeId(configureNode.getFlowId());
                break;
            }
            processNodeTemp = (ProcessNode) processNodeTemp.getNextNode();
        }
        List<ProcessNode> processNodes = new ArrayList<>();
        List<Approve> approves = new ArrayList<>();
        tree2Collection(businessId, processNode, processNodes, approves);
        mapper.deleteNode(this.processId);
        mapper.batchInsertNode(processNodes);
        mapper.deleteApprove(this.processId);
        mapper.batchInsertApprove(approves);
        //修改流程状态
        result += mapper.queryById(this.processId).turnDown(mapper);
        return result;
    }

    /**
     * 替换ParentNodeId，NextNodeId的nodeId为flowId
     *
     * @param processNodes
     * @return
     */
    public static void replaceNodeIdToFlowId(List<ProcessNode> processNodes) {
        for (ProcessNode processNode : processNodes) {
            for (ProcessNode otherNode : processNodes) {
                if (processNode.getParentNodeId() != null && processNode.getParentNodeId().equals(otherNode.getNodeId())) {
                    processNode.setParentNodeId(otherNode.getFlowId());
                }
                if (processNode.getNextNodeId() != null && processNode.getNextNodeId().equals(otherNode.getNodeId())) {
                    processNode.setNextNodeId(otherNode.getFlowId());
                }
            }
        }
    }

    public static ProcessNode buildTree(List<ProcessNode> configureNodes) {
        replaceNodeIdToFlowId(configureNodes);
        ProcessNode processNode = null;
        for (ProcessNode configureNode : configureNodes) {
            if (configureNode.getSign() == 0) {
                processNode = configureNode;
            }
        }
        buildTree(configureNodes, processNode);
        return processNode;
    }

    public static void buildTree(List<ProcessNode> configureNodes, ProcessNode processNode) {
        for (ProcessNode configureNode : configureNodes) {
            if (Objects.equals(processNode.getFlowId(), configureNode.getParentNodeId())) {
                processNode.setNextNode(configureNode);
                buildTree(configureNodes, configureNode);
            }
        }
    }

    public static void tree2Collection(String businessId, ProcessNode processNode, List<ProcessNode> processNodes, List<Approve> approves) {
        String processId = processNode.getProcessId();
        while (true) {
            processNode.setProcessId(processId);
            processNodes.add(processNode);
            List<Approve> approveList = processNode.getApproves();
            for (Approve approve : approveList) {
                approve.setProcessId(processId);
                approve.setFlowId(processNode.getFlowId());
                approve.setBusinessId(businessId);
                approves.add(approve);
            }
            if (processNode.getNextNode() == null) {
                break;
            }
            processNode = (ProcessNode) processNode.getNextNode();
        }
    }
}
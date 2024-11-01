package service.impl;


import bean.*;
import bean.Process;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import mapper.ProcessConfigureMapper;
import mapper.ProcessManagementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import service.ProcessConfigureService;
import service.ProcessHandle;
import service.ProcessManagementService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:41
 */
@Service
public class ProcessManagementServiceImpl implements ProcessManagementService {
    @Resource
    private ProcessManagementMapper managementMapper;
    @Resource
    private ProcessConfigureMapper configureMapper;
    @Resource
    private ProcessConfigureService configureService;

    @Resource
    private ProcessHandle handle;

    /**
     * 新增流程
     *
     * @param param 参数
     * @return 流程id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addProcess(Map<String, Object> param) {
        String configureId = param.get("configureId").toString();
        String businessId = param.get("businessId").toString();
        Configure configure = configureMapper.queryById(configureId);
        List<ConfigureNode> configureNodes = configureMapper.queryAllNodes(configureId);
        if (configure == null || CollectionUtils.isEmpty(configureNodes)) {
            return 0;
        }
        String userId = "username";
        String userName = "display_name";
        String processId = UUID.randomUUID().toString();
        Process process = new bean.Process(configure, processId, userId, userName, businessId);
        // 新增流程
        managementMapper.addProcess(process);
        // 新增节点
        List<ProcessNode> processNodes = new ArrayList<>(configureNodes.size());
        List<Approve> approves = new ArrayList<>();
        //发起人节点临时存储
        Approve initiator = new Approve();
        for (ConfigureNode configureNode : configureNodes) {
            String flowId = UUID.randomUUID().toString();
            ProcessNode processNode = new ProcessNode(configureNode, processId, flowId);
            //发起人节点
            if (configureNode.getSign() == 0) {
                initiator = new Approve(configureId, configureNode.getNodeId(), userId, userName, processId, flowId, businessId);
                approves.add(initiator);
            }
            //其他节点
            else {
                List<People> people = configureNode.getPeoples();
                if (!CollectionUtils.isEmpty(people)) {
                    for (People person : people) {
                        approves.add(new Approve(person, processId, flowId, businessId));
                    }
                }
            }
            processNodes.add(processNode);
        }
        ProcessNode.replaceNodeIdToFlowId(processNodes);
        managementMapper.batchInsertNode(processNodes);
        managementMapper.batchInsertApprove(approves);
        //流程发起人自动审批开始节点
        initiator.setStatus(1);
        processApprove(initiator);
        return 1;
    }

    /**
     * 审批流程
     *
     * @param param 参数
     * @return 状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int processApprove(Approve param) {
        int result = 0;
        String processId = param.getProcessId();
        int status = param.getStatus();
        if (StringUtils.isEmpty(processId) || status < 1) {
            return 0;
        }
        String flowId = param.getFlowId();
        String userId = "username";
        Approve approve = managementMapper.queryApprove(flowId, userId);
        // 同意
        if (status == 1) {
            result += approve.approve(handle, param.getRemark());
        }
        // 拒绝
        if (status == 2) {
            result += approve.sendBack(handle, param.getRemark());
        }
        // 驳回
        if (status == 3) {
            result += approve.turnDown(handle, param.getRemark());
        }
        return result;
    }

    /**
     * 待办/已办-列表
     *
     * @return 分页
     */
    @Override
    public PageInfo<Map<String, Object>> upcomingList(int type, int pageNumber, int pageSize) {
        PageMethod.startPage(pageNumber, pageSize);
        String userId = "username";
        return new PageInfo<>(managementMapper.upcomingList(userId, type));
    }


    /**
     * 我的待办/已办-查看审批
     *
     * @return 结果对象
     */
    @Override
    public Map<String, Object> getUpcoming(String flowId) {
        String userId = "username";
        Map<String, Object> map = managementMapper.upcomingDetails(flowId, userId);
        map.put("business", managementMapper.businessDetails(flowId, userId));
        return map;
    }

    @Override
    public Graph showApprove(String processId) {
        Graph graph = configureService.buildGraph(managementMapper.queryAllNodesOfTree(processId));
        graph.setProcess(managementMapper.queryById(processId));
        return graph;
    }

    /**
     * 我的发起-列表
     *
     * @return 分页
     */
    @Override
    public PageInfo<Map<String, Object>> listInitiate(int pageNumber, int pageSize) {
        String userId = "username";
        PageMethod.startPage(pageNumber, pageSize);
        return new PageInfo<>(managementMapper.queryByUser(userId));
    }
}

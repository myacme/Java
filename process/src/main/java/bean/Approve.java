package bean;

import service.ProcessHandle;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午10:21
 */
@Component
public class Approve extends People {

    public Approve() {
    }

    public Approve(Approve approve) {
        super(approve.getConfigureId(), approve.getNodeId(), approve.getUserId(), approve.getUserName());
        this.processId = approve.getProcessId();
        this.flowId = approve.getFlowId();
        this.businessId = approve.getBusinessId();
    }

    public Approve(People people, String processId, String flowId, String businessId) {
        super(people);
        this.processId = processId;
        this.flowId = flowId;
        this.businessId = businessId;
        this.status = -1;
    }

    public Approve(String configureId, String nodeId, String userId, String userName, String processId, String flowId, String businessId) {
        super(configureId, nodeId, userId, userName);
        this.processId = processId;
        this.flowId = flowId;
        this.businessId = businessId;
    }

    /**
     * 运行ID
     */
    private String processId;

    /**
     * 流转ID
     */
    private String flowId;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 审批标志
     * -1--未开始
     * 0--未审批
     * 1--已审批
     * 2--未通过
     */
    private int status;

    /**
     * 审批日期
     */
    private Date approvalDate;

    /**
     * 备注
     */
    private String remark;

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

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Transactional(rollbackFor = Exception.class)
    public int approve(ProcessHandle mapper, String remark) {
        this.status = 1;
        this.remark = remark;
        int result = mapper.approveOfUser(this);
        //修改节点
        ProcessNode processNode = mapper.queryNode(this.flowId);
        result += processNode.approve(mapper);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int sendBack(ProcessHandle mapper, String remark) {
        this.status = 2;
        this.remark = remark;
        int result = mapper.approveOfUser(this);
        //修改节点
        result += mapper.queryNode(this.flowId).sendBack(mapper);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int turnDown(ProcessHandle mapper, String remark) {
        this.status = 3;
        this.remark = remark;
        int result = mapper.approveOfUser(this);
        //修改节点
        result += mapper.queryNode(this.flowId).turnDown(mapper, this.getBusinessId());
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void toDo(ProcessHandle mapper) {
        this.status = 0;
        mapper.approveOfUser(this);
    }
}

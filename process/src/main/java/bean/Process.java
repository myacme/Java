package bean;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.ProcessHandle;

import java.util.Date;

/**
 * 审批流程节点实体类
 *
 * @author ljx
 * @since 2021-09-24 08:56:52
 */
/*
CREATE TABLE `d_flow_work` (
  `configure_id` varchar(32) NOT NULL,
  `process_id` varchar(32) NOT NULL COMMENT '运行id',
  `business_id` varchar(32) NOT NULL COMMENT '业务id',
  `initiator` varchar(255) NOT NULL COMMENT '发起人',
  `initiator_name` varchar(255) NOT NULL COMMENT '发起人name',
  `initiator_date` datetime NOT NULL COMMENT '发起时间',
  `status` int(1) DEFAULT NULL COMMENT '      状态\r\n     0 审批中\r\n     1 已通过\r\n     2 未通过',
  `approval_date` datetime DEFAULT NULL,
  PRIMARY KEY (`process_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
*/
@Component
public class Process extends Configure {

    public Process() {
    }

    public Process(Configure configure, String processId, String initiator,String initiatorName, String businessId) {
        super(configure);
        this.processId = processId;
        this.initiator = initiator;
        this.initiatorName = initiatorName;
        this.initiatorDate = new Date();
        this.businessId = businessId;
    }

    /**
     * 运行ID
     */
    private String processId;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 发起人
     */
    private String initiator;

    /**
     * 发起人
     */
    private String initiatorName;

    /**
     * 发起时间
     */
    private Date initiatorDate;

    /**
     * 状态
     * 0 审批中
     * 1 已通过
     * 2 未通过
     */
    private int status;

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

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public Date getInitiatorDate() {
        return initiatorDate;
    }

    public void setInitiatorDate(Date initiatorDate) {
        this.initiatorDate = initiatorDate;
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

    @Transactional(rollbackFor = Exception.class)
    public int approve(ProcessHandle mapper) {
        this.status = 1;
        return mapper.approveOfProcess(this);
    }

    @Transactional(rollbackFor = Exception.class)
    public int sendBack(ProcessHandle mapper) {
        this.status = 2;
        return mapper.approveOfProcess(this);
    }
    @Transactional(rollbackFor = Exception.class)
    public int turnDown(ProcessHandle mapper) {
        this.status = 3;
        return mapper.approveOfProcess(this);
    }
}
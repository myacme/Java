package service;


import bean.Approve;
import bean.Process;
import bean.ProcessNode;

import java.util.List;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */
public interface ProcessHandle {

    Process queryById(String processId);

    ProcessNode queryNode(String flowId);

    int approveOfProcess(Process param);

    int approveOfNode(ProcessNode param);

    int approveOfUser(Approve param);

    int enableBusiness(String processId);

    List<ProcessNode> queryAllNodesOfConfigureTree(String configureId);

    ProcessNode queryAllNodesOfProcessTree(String processId);

    void batchInsertNode(List<ProcessNode> list);

    void batchInsertApprove(List<Approve> list);

    int deleteNode(String processId);

    int deleteApprove(String processId);
}
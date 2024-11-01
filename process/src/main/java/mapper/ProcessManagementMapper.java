package mapper;


import bean.Approve;
import bean.Process;
import bean.ProcessNode;

import java.util.List;
import java.util.Map;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */
public interface ProcessManagementMapper {

    List<ProcessNode> queryAllNodesOfConfigureTree(String configureId);

    List<Approve> queryApprovesByNodeidOfConfigure(String flowId);

    Process queryById(String processId);

    List<Map<String, Object>> queryByUser(String userId);

    int addProcess(Process process);

    ProcessNode queryNode(String flowId);

    ProcessNode queryAllNodesOfTree(String processId);

    ProcessNode queryNodesByParentId(String parentNodeId);

    int batchInsertNode(List<ProcessNode> list);

    int deleteNode(String processId);


    /**
     * 查询节点审批人员
     *
     * @param flowId 参数
     * @return 对象列表
     */
    List<Approve> queryApprovesByNodeid(String flowId);

    Approve queryApprove(String flowId, String userId);

    int batchInsertApprove(List<Approve> list);

    int approveOfProcess(Process param);

    int approveOfNode(ProcessNode param);

    int approveOfUser(Approve param);

    int deleteApprove(String processId);

    List<Map<String, Object>> upcomingList(String userId, int type);

    Map<String, Object> upcomingDetails(String flowId,String userId);

    Map<String, Object> businessDetails(String flowId,String userId);
}
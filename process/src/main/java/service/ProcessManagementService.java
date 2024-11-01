package service;


import bean.Approve;
import bean.Graph;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */

public interface ProcessManagementService {

    /**
     * 新增流程
     *
     * @param param
     * @return 流程id
     */
    int addProcess(Map<String, Object> param);

    /**
     * 审批流程
     *
     * @param param 参数
     * @return 结果
     */
    int processApprove(Approve param);

    /**
     * 待办/已办-列表
     *
     * @return 分页
     */
    PageInfo<Map<String, Object>> upcomingList(int type, int pageNumber, int pageSize);

    /**
     * 查询审批详情
     *
     * @param flowId 参数
     * @return 详情
     */
    Map<String, Object> getUpcoming(String flowId);

    Graph showApprove(String processId);

    /**
     * 我的发起-列表
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> listInitiate(int pageNumber, int pageSize);


}
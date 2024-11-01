package controller;


import bean.Approve;
import org.springframework.web.bind.annotation.*;
import service.ProcessManagementService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */


@RestController
@RequestMapping("/api/process/manage")
public class ProcessManagementController {

    @Resource
    private ProcessManagementService processManagementService;




    @PostMapping("/add")
    public Object add(@RequestBody Map<String, Object> param) {
        try {
            return processManagementService.addProcess(param);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 我的待办/已办-列表
     *
     * @param type 类型  0 待办   1 已办
     * @return 结果对象
     */
    @GetMapping("/upcoming/list")
    public Object listData(int type, int pageNumber, int pageSize) {
        try {
            return processManagementService.upcomingList(type, pageNumber, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 我的待办-详情
     *
     * @param flowId 参数
     * @return 结果对象
     */
    @GetMapping("/upcoming/get")
    public Object selectOne(String flowId) {
        try {
            return processManagementService.getUpcoming(flowId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 我的待办-审批
     *
     * @param param 参数
     * @return 结果对象
     */
    @PostMapping("/upcoming/approve")
    public Object approve(@RequestBody Approve param) {
        try {
            return processManagementService.processApprove(param);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 我的待办/已办-查看审批
     *
     * @return 结果对象
     */
    @GetMapping("/upcoming/show-approve")
    public Object showApprove(String processId) {
        try {
            return processManagementService.showApprove(processId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 我的待办-列表
     *
     * @return 结果对象
     */
    @GetMapping("/upcoming/initiate")
    public Object listInitiate(int pageNumber, int pageSize) {
        try {
            return processManagementService.listInitiate(pageNumber, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
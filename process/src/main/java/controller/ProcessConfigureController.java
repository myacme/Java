package controller;


import bean.Configure;
import bean.Graph;
import org.springframework.web.bind.annotation.*;
import service.ProcessConfigureService;

import javax.annotation.Resource;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */
@RestController
@RequestMapping("/api/process/configure")
public class ProcessConfigureController {

    @Resource
    private ProcessConfigureService processConfigureService;

    /**
     * 流程维护列表
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Object listData(int pageNumber, int pageSize) {
        try {
            return processConfigureService.queryAll(pageNumber, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 新增流程配置数据
     *
     * @param param 参数
     * @return 结果对象
     */
    @PostMapping("/add")
    public Object add(@RequestBody Configure param) {
        try {
            return processConfigureService.add(param);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 查询流程配置数据
     *
     * @param configureId id
     * @return 结果对象
     */
    @GetMapping("/get-configure")
    public Object selectOne(String configureId) {
        try {
            return processConfigureService.queryById(configureId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 新增流程配置数据
     *
     * @param graph 参数
     * @return 结果对象
     */
    @PostMapping("/add-configure")
    public Object addConfigure(@RequestBody Graph graph) {
        try {
            return processConfigureService.insert(graph);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 修改流程配置数据
     *
     * @param graph 参数
     * @return 结果对象
     */
    @PostMapping("/modify-configure")
    public Object update(@RequestBody Graph graph) {
        try {
            return processConfigureService.update(graph);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 通过主键删除流程配置数据
     *
     * @param configureId 参数
     * @return 结果对象
     */
    @GetMapping("/delete")
    public Object delete(String configureId) {
        try {
            return processConfigureService.deleteById(configureId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 查询默认审批人员
     *
     * @param nodeId 参数
     * @return 结果对象
     */
    @GetMapping("/default-people")
    public Object queryAllOfDefaultPeople(String nodeId) {
        try {
            return processConfigureService.queryAllOfDefaultPeople(nodeId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 查询审批人员码表
     *
     * @return 结果对象
     */
    @GetMapping("/people-select")
    public Object peopleSelect() {
        try {
            return processConfigureService.peopleSelect();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
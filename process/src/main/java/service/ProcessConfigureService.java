package service;


import bean.Configure;
import bean.ConfigureNode;
import bean.Graph;
import bean.People;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */
public interface ProcessConfigureService {

    /**
     * 通过ID查询单条配置数据
     *
     * @param configureId 参数
     * @return 实例对象
     */
    Graph queryById(String configureId);

    /**
     * 查询多条数据
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> queryAll(int pageNumber, int pageSize);

    /**
     * 新增数据
     *
     * @param param 参数
     * @return 影响行数
     */
    int add(Configure param);

    /**
     * 新增配置数据
     *
     * @param graph 参数
     * @return 影响行数
     */
    int insert(Graph graph);

    /**
     * 修改配置数据
     *
     * @param graph 参数
     * @return 影响行数
     */
    int update(Graph graph);

    /**
     * 通过主键id删除数据
     *
     * @param configureId 参数
     * @return 影响行数
     */
    int deleteById(String configureId);

    /**
     * 查询默认审批人员
     *
     * @param nodeId 参数
     * @return 列表
     */
    List<People> queryAllOfDefaultPeople(String nodeId);

    List<People> peopleSelect();

    Graph buildGraph(ConfigureNode node);
}
package mapper;


import bean.Configure;
import bean.ConfigureNode;
import bean.People;

import java.util.List;
import java.util.Map;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */
public interface ProcessConfigureMapper {

    Configure queryById(String configureId);

    ConfigureNode queryAllNodesOfTree(String configureId);

    ConfigureNode queryNodesByParentId(String parentNodeId);

    List<ConfigureNode> queryAllNodes(String configureId);

    List<Map<String, Object>> queryAll();

    int addConfigure(Configure param);

    int updateConfigure(Configure param);

    int deleteConfigure(String configureId);


    /**
     * 批量新增节点
     *
     * @param list 实例对象的集合
     * @return 影响行数
     */
    int batchInsertNode(List<ConfigureNode> list);

    /**
     * 删除节点
     *
     * @param configureId
     * @return 影响行数
     */
    int deleteNode(String configureId);


    /**
     * 查询节点审批人员
     *
     * @param nodeId 参数
     * @return 对象列表
     */
    List<People> queryPeoplesByNodeid(String nodeId);

    /**
     * 批量新增审批人员
     *
     * @param list 实例对象的集合
     * @return 影响行数
     */
    int batchInsertPeople(List<People> list);

    /**
     * 删除
     *
     * @param configureId
     * @return 影响行数
     */
    int deletePeople(String configureId);


    List<People> peopleSelect(String userId);
}
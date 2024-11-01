package service.impl;

import bean.*;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import mapper.ProcessConfigureMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ProcessConfigureService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */
@Service
public class ProcessConfigureServiceImpl implements ProcessConfigureService {
    @Resource
    private ProcessConfigureMapper processConfigureMapper;


    /**
     * 通过ID查询单条数据
     *
     * @param configureId 参数
     * @return map
     */
    @Override
    public Graph queryById(String configureId) {
        Graph graph = buildGraph(processConfigureMapper.queryAllNodesOfTree(configureId));
        graph.setProcess(processConfigureMapper.queryById(configureId));
        return graph;
    }

    /**
     * 查询多条数据
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> queryAll(int pageNumber, int pageSize) {
        try {
            PageMethod.startPage(pageNumber, pageSize);
            return new PageInfo<>(processConfigureMapper.queryAll());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new PageInfo<>();
    }

    /**
     * 新增数据
     *
     * @param param 参数
     * @return 影响行数
     */
    @Override
    public int add(Configure param) {
        param.setConfigureId(UUID.randomUUID().toString());
        return processConfigureMapper.addConfigure(param);
    }


    /**
     * 通过主键id删除数据
     *
     * @param configureId 参数
     * @return 影响行数
     */
    @Override
    public int deleteById(String configureId) {
        return processConfigureMapper.deleteConfigure(configureId);
    }


    /**
     * 新增配置数据
     *
     * @param graph 参数
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Graph graph) {
        int result = 0;
        Configure process = graph.getProcess();
        List<ConfigureNode> nodes = graph.getNodes();
        List<Line> lines = graph.getLines();
        List<People> people = new ArrayList<>();
        result += processConfigureMapper.updateConfigure(process);
        nodes.forEach(node -> {
            lines.forEach(line -> {
                if (line.getEnd().equals(node.getNodeId())) {
                    node.setParentNodeId(line.getStart());
                }
                if (line.getStart().equals(node.getNodeId())) {
                    node.setNextNodeId(line.getEnd());
                }
            });
            List<People> peoples = node.getPeoples();
            peoples.forEach(person -> {
                person.setNodeId(node.getNodeId());
                person.setConfigureId(node.getConfigureId());
            });
            people.addAll(peoples);
        });
        result += processConfigureMapper.batchInsertNode(nodes);
        result += processConfigureMapper.batchInsertPeople(people);
        return result;
    }

    /**
     * 修改数据
     *
     * @param graph 参数
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Graph graph) {
        int result = 0;
        Configure process = graph.getProcess();
        List<ConfigureNode> nodes = graph.getNodes();
        List<Line> lines = graph.getLines();
        List<People> people = new ArrayList<>();
        result += processConfigureMapper.updateConfigure(process);
        nodes.forEach(node -> {
            lines.forEach(line -> {
                if (line.getEnd().equals(node.getNodeId())) {
                    node.setParentNodeId(line.getStart());
                }
                if (line.getStart().equals(node.getNodeId())) {
                    node.setNextNodeId(line.getEnd());
                }
            });
            List<People> peoples = node.getPeoples();
            peoples.forEach(person -> {
                person.setNodeId(node.getNodeId());
                person.setConfigureId(node.getConfigureId());
            });
            people.addAll(peoples);
        });
        processConfigureMapper.deleteNode(process.getConfigureId());
        result += processConfigureMapper.batchInsertNode(nodes);
        processConfigureMapper.deletePeople(process.getConfigureId());
        result += processConfigureMapper.batchInsertPeople(people);
        return result;
    }

    /**
     * 查询默认审批人员
     *
     * @param nodeId 参数
     * @return 列表
     */
    @Override
    public List<People> queryAllOfDefaultPeople(String nodeId) {
        return processConfigureMapper.queryPeoplesByNodeid(nodeId);
    }

    @Override
    public List<People> peopleSelect() {
        String userId = "username";
        return processConfigureMapper.peopleSelect(userId);
    }

    /**
     * 构建 流程节点列表与节点关系列表
     *
     * @param node 流程
     * @return 流程节点列表与节点关系列表
     */
    @Override
    public Graph buildGraph(ConfigureNode node) {
        List<ConfigureNode> nodes = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        if (node != null) {
            while (true) {
                ConfigureNode next = node.getNextNode();
                nodes.add(node);
                if (next == null) {
                    break;
                }
                Line line = new Line(node.getNodeId(), next.getNodeId());
                lines.add(line);
                node = next;
            }
        }
        return new Graph(nodes, lines);
    }
}
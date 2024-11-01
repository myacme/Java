package service.impl;


import bean.Approve;
import bean.Process;
import bean.ProcessNode;
import mapper.ProcessConfigureMapper;
import mapper.ProcessManagementMapper;
import org.springframework.stereotype.Component;
import service.ProcessHandle;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/29 上午11:42
 */
@Component
public class ProcessHandleImpl implements ProcessHandle {

    @Resource
    private ProcessManagementMapper managementMapper;

    @Resource
    private ProcessConfigureMapper configureMapper;

    @Override
    public Process queryById(String processId) {
        return managementMapper.queryById(processId);
    }

    @Override
    public ProcessNode queryNode(String flowId) {
        return managementMapper.queryNode(flowId);
    }

    @Override
    public int approveOfProcess(Process param) {
        return managementMapper.approveOfProcess(param);
    }

    @Override
    public int approveOfNode(ProcessNode param) {
        return managementMapper.approveOfNode(param);
    }

    @Override
    public int approveOfUser(Approve param) {
        return managementMapper.approveOfUser(param);
    }

    @Override
    public List<ProcessNode> queryAllNodesOfConfigureTree(String configureId) {
        return managementMapper.queryAllNodesOfConfigureTree(configureId);
    }

    @Override
    public ProcessNode queryAllNodesOfProcessTree(String processId) {
        return managementMapper.queryAllNodesOfTree(processId);
    }

    @Override
    public void batchInsertNode(List<ProcessNode> list) {
        managementMapper.batchInsertNode(list);
    }

    @Override
    public void batchInsertApprove(List<Approve> list) {
        managementMapper.batchInsertApprove(list);
    }

    @Override
    public int deleteNode(String processId) {
        return managementMapper.deleteNode(processId);
    }

    @Override
    public int deleteApprove(String processId) {
        return managementMapper.deleteApprove(processId);
    }

    @Override
    public int enableBusiness(String processId) {
        return 1;
    }
}
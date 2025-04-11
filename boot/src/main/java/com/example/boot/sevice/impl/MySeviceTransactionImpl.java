package com.example.boot.sevice.impl;


import com.example.boot.mapper.MyMapper;
import com.example.boot.sevice.MySevice;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/12/27 上午10:15
 */
@Service
public class MySeviceTransactionImpl implements MySevice {

/*
    @Transactional 默认捕获runtimeException

    声明式事务（@Transactional）：DataSourceTransactionManager  doBegin 获取dataSource 放入ThreadLocal里

    事务：
    1、A方法有@Transactional注解，B方法有，没有@Transactional注解，A调用B方法，都会事务成功
    2、A方法没有@Transactional注解，B方法有@Transactional注解，A调用B方法，事务失效
        解决方法：
            1、自调用  @Resource注入自己
            2、通过AopContext.currentProxy()获取代理对象，调用方法

    3、多线程事务：主线程有@Transactional注解
        主线程：ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        子线程：TransactionSynchronizationManager.bindResource(dataSource, connectionHolder);
 */

    @Resource
    private MyMapper mapper;

    /**
     * 自调用  A方法没有@Transactional注解，B方法有@Transactional注解，A调用B方法，事务失效
     */
    @Resource
    MySeviceTransactionImpl mySeviceTransactionImpl;

    @Resource
    DataSource dataSource;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void helloAop(String name) {
        mapper.insertA();
        //this调用事务失效 ； 自调用 事务生效
//        mySeviceTransactionImpl.helloAop1(name);
        //自调用 事务成功  获取当前类的动态代理对象
//        ((MySeviceTransactionImpl) AopContext.currentProxy()).helloAop1(name);
//        mapper.insertA1();
        // 多线程事务
//        从 ThreadLocal<Map<Object, Object>> resources 中获取到 ConnectionHolder 对象，
        ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
        new Thread(() -> {
            // 将 ConnectionHolder 对象绑定到当前线程的 ThreadLocal 中
            TransactionSynchronizationManager.bindResource(dataSource, connectionHolder);
            mapper.insertA();
        }).start();
    }

    @Transactional(rollbackFor = Exception.class)
    public void helloAop1(String name) {
        mapper.insertA();
//        int i = 1 / 0;
    }
}

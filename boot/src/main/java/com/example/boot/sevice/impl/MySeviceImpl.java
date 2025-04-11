package com.example.boot.sevice.impl;


import com.example.boot.mapper.MyMapper;
import com.example.boot.sevice.MySevice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/12/27 上午10:15
 */
@Service
public class MySeviceImpl implements MySevice {


    @Resource
    private MyMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void helloAop(String name) {
        mapper.insertA();
        mapper.insertA();
    }
}

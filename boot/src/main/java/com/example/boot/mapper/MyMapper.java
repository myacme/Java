package com.example.boot.mapper;


import org.apache.ibatis.annotations.Mapper;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/12/27 上午10:18
 */
@Mapper
public interface MyMapper {

    int insert(int v);
    int insert1(int v);

}

package javatest;


import javatest.entity.ResultFileDO;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author MyAcme
 */
public class StreamDemo {

    // 统一文件处理方法
    static BiConsumer<String, String> forEach = (currentNode, file) -> {
    };

    private static <T> Stream<T> safeStream(List<T> list) {
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream();
    }


    public static void test(List<ResultFileDO> list) {
        //两个字段分组
        Map<String, Map<String, List<ResultFileDO>>> resultFiles = safeStream(list)
                .collect(Collectors.groupingBy(
                        ResultFileDO::getConfigId,
                        Collectors.groupingBy(ResultFileDO::getProjectId)
                ));
        //使用
        (new HashMap<String, List<String>>()).getOrDefault("", Collections.emptyList())
                .stream()
                .map(map -> "ProjectId")
                .map(subProjectId -> resultFiles.getOrDefault("ConfigId", Collections.emptyMap())
                        .getOrDefault("ProjectId", Collections.emptyList()))
                .flatMap(List::stream)
                .forEach(file -> forEach.accept("", ""));
    }

    public static void test2(List<ResultFileDO> list) {
        //ConfigId为key
        Map<Object, ResultFileDO> favorite = list.stream()
                .collect(Collectors.toMap(ResultFileDO::getConfigId, Function.identity()));
    }

    public static void main(String[] args) {
    }
}


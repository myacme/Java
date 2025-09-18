package util;


import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 *
 * sql 工具类
 *
 * @author ljx
 * @version 1.0.0
 * @create 2025/9/2 13:35
 */
//@Component
public class SqlUtil {

    /**
     * 分批处理列表数据
     *
     * @param list      待处理列表
     * @param batchSize 批次大小
     * @param consumer  处理函数
     * @param <T>       数据类型
     */

    public static <T> void batchProcess(List<T> list, int batchSize, Consumer<List<T>> consumer) {
        IntStream.range(0, (list.size() + batchSize - 1) / batchSize)
                .forEach(i -> {
                    int start = i * batchSize;
                    int end = Math.min((i + 1) * batchSize, list.size());
                    List<T> batch = list.subList(start, end);
                    consumer.accept(batch);
                });
    }
}

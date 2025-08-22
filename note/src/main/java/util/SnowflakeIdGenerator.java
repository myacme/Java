package util;

/**
 * Snowflake ID 生成器
 * <p>
 * Snowflake 算法生成的 ID 是一个 64 位的整数，由以下几部分组成：
 * <ul>
 *   <li>1 位符号位：始终为 0，表示正数</li>
 *   <li>41 位时间戳：表示从自定义的开始时间到当前时间的毫秒数</li>
 *   <li>5 位数据中心 ID</li>
 *   <li>5 位机器 ID</li>
 *   <li>12 位序列号：用于同一毫秒内生成多个 ID</li>
 * </ul>
 * </p>
 *
 * @author MyAcme
 */
public class SnowflakeIdGenerator {

    /**
     * 系统开始时间戳（2010-11-04 09:42:54.657 UTC）
     * 这个时间戳用于计算相对时间，以减少时间戳的位数
     */
    private static final long START_TIMESTAMP = 1288834974657L;

    /**
     * 序列号占用的位数
     */
    private static final long SEQUENCE_BITS = 12;

    /**
     * 机器ID占用的位数
     */
    private static final long WORKER_BITS = 5;

    /**
     * 数据中心ID占用的位数
     */
    private static final long DATA_CENTER_BITS = 5;

    /**
     * 序列号的最大值（4095）
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    /**
     * 机器ID的最大值（31）
     */
    private static final long MAX_WORKER = ~(-1L << WORKER_BITS);

    /**
     * 数据中心ID的最大值（31）
     */
    private static final long MAX_DATA_CENTER = ~(-1L << DATA_CENTER_BITS);

    /**
     * 机器ID左移位数（12位）
     */
    private static final long WORKER_LEFT = SEQUENCE_BITS;

    /**
     * 数据中心ID左移位数（17位）
     */
    private static final long DATA_CENTER_LEFT = SEQUENCE_BITS + WORKER_BITS;

    /**
     * 时间戳左移位数（22位）
     */
    private static final long TIMESTAMP_LEFT = SEQUENCE_BITS + WORKER_BITS + DATA_CENTER_BITS;

    /**
     * 上一次生成ID的时间戳
     */
    private static long lastTimestamp = -1L;

    /**
     * 当前毫秒内的序列号
     */
    private static long sequence = 0L;

    /**
     * 机器ID
     */
    private final long workerId;

    /**
     * 数据中心ID
     */
    private final long dataCenterId;

    /**
     * 构造一个新的 Snowflake ID 生成器
     *
     * @param workerId     机器ID，范围 [0, 31]
     * @param dataCenterId 数据中心ID，范围 [0, 31]
     * @throws IllegalArgumentException 如果 workerId 或 dataCenterId 超出有效范围
     */
    public SnowflakeIdGenerator(long workerId, long dataCenterId) {
        if (workerId > MAX_WORKER || workerId < 0) {
            throw new IllegalArgumentException("Worker ID 无效");
        }
        if (dataCenterId > MAX_DATA_CENTER || dataCenterId < 0) {
            throw new IllegalArgumentException("数据中心ID 无效");
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 生成下一个唯一的 Snowflake ID
     *
     * @return 生成的 64 位 ID
     *
     * @throws RuntimeException 如果系统时钟回拨
     */
    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨");
        }
        if (currentTimestamp == lastTimestamp) {
            // 同一毫秒内，序列号+1
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // 当前毫秒序列号用尽，等待下一毫秒
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 新的一毫秒，序列号从0开始
            sequence = 0;
        }
        lastTimestamp = currentTimestamp;
        return (currentTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT
                | dataCenterId << DATA_CENTER_LEFT
                | workerId << WORKER_LEFT
                | sequence;
    }

    /**
     * 等待直到下一个毫秒的到来
     *
     * @param lastTimestamp 上一次生成ID的时间戳
     * @return 下一个毫秒的时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 测试方法，生成并打印10个ID
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(10, 10);
        for (int i = 0; i < 10; i++) {
            long id = idGenerator.nextId();
            System.out.println("Generated ID: " + id);
        }
    }
}

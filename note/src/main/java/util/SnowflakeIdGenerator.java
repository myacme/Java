package util;

public class SnowflakeIdGenerator {

    // 系统开始时间戳（2023-01-01）
    private final long START_TIMESTAMP = 1672531200000L;

    // 各部分所占位数
    private final long SEQUENCE_BITS = 12;  // 序列号占用的位数
    private final long WORKER_BITS = 5;     // 机器ID占用的位数
    private final long DATA_CENTER_BITS = 5; // 数据中心ID占用的位数

    // 最大值
    private final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS); // 4095
    private final long MAX_WORKER = ~(-1L << WORKER_BITS);     // 31
    private final long MAX_DATA_CENTER = ~(-1L << DATA_CENTER_BITS); // 31

    // 位移量
    private final long WORKER_LEFT = SEQUENCE_BITS;
    private final long DATA_CENTER_LEFT = SEQUENCE_BITS + WORKER_BITS;
    private final long TIMESTAMP_LEFT = SEQUENCE_BITS + WORKER_BITS + DATA_CENTER_BITS;

    private long lastTimestamp = -1L;
    private long sequence = 0L;
    private final long workerId;
    private final long dataCenterId;

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

    // 生成下一个ID
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

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);
        for (int i = 0; i < 10; i++) {
            long id = idGenerator.nextId();
            System.out.println("Generated ID: " + id);
        }
    }
}

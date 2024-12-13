package algorithm;


import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 深度搜索
 *
 * @author ljx
 * @version 1.0.0
 * @create 2024/12/9 下午5:42
 */
public class DFS {

    static class Point implements Comparable<Point> {
        private int x;

        private int y;

        private int able = 1;

        private Point last;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public PriorityQueue<Point> getNext(Point start, Point end) {
            PriorityQueue<Point> next = new PriorityQueue<>();
            if (this.x > 0 && MAP[this.x - 1][this.y].able == 1) {
                next.add(MAP[this.x - 1][this.y]);
            }
            if (this.x < 9 && MAP[this.x + 1][this.y].able == 1) {
                next.add(MAP[this.x + 1][this.y]);
            }
            if (this.y > 0 && MAP[this.x][this.y - 1].able == 1) {
                next.add(MAP[this.x][this.y - 1]);
            }
            if (this.y < 9 && MAP[this.x][this.y + 1].able == 1) {
                next.add(MAP[this.x][this.y + 1]);
            }
            return next;
        }

        @Override
        public String toString() {
            return x + "-" + y;
        }

        @Override
        public int compareTo(Point o) {
            return (this.x * 10 + this.y) - (o.x * 10 + o.y);
        }
    }


    private static final Point[][] MAP = new Point[10][10];

    public static void initializationGraph() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                MAP[i][j] = new Point(i, j);
            }
        }
        obstacles(null);
    }

    public static void obstacles(Point point) {
        for (int i = 0; i < 6; i++) {
            MAP[i + 2][7].able = 0;
            MAP[2][i + 1].able = 0;
            MAP[7][i + 1].able = 0;
        }
    }

    public static void getPath(Point start, Point end) {
        List<Point> list = new ArrayList<>();
        while (true) {
            end.able = 8;
            list.add(end);
            if (end == start) {
                break;
            }
            end = end.last;
        }
    }

    public static void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(MAP[i][j].able + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Point findPath(Point start, Point end) {
        PriorityQueue<Point> next = start.getNext(start, end);
        start.able = 2;
        print();
        if (start.x == end.x && start.y == end.y) {
            start.last = end;
            return end;
        }
        if (!next.isEmpty()) {
            while (!next.isEmpty()) {
                Point point = next.poll();
                start.last = point;
                Point path = findPath(point, end);
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        initializationGraph();
        Point start = MAP[5][0];
        Point end = MAP[5][9];
        LocalTime startTime = LocalTime.now();
        Point able = findPath(start, end);
        Duration duration = Duration.between(startTime, LocalTime.now());
        System.out.println(duration.toMillis());
        if (able == end) {
            getPath(end, start);
        }
        print();
    }
}

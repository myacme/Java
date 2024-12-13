package algorithm;


import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 迪杰斯特拉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2024/12/11 下午3:06
 */
public class Dijkstra {

    static class Point implements Comparable<Point> {
        private int x;

        private int y;

        private int cost;

        private int foreCast;

        private int able = 1;

        private int priority;

        private Point last;

        public Point(int x, int y) {
            this.y = y;
            this.x = x;
        }

        public List<Point> getNext(Point start, Point end) {
            List<Point> next = new ArrayList<>();
            if (this.x > 0) {
                next.add(MAP[this.x - 1][this.y]);
            }
            if (this.x < 9) {
                next.add(MAP[this.x + 1][this.y]);
            }
            if (this.y > 0) {
                next.add(MAP[this.x][this.y - 1]);
            }
            if (this.y < 9) {
                next.add(MAP[this.x][this.y + 1]);
            }
            return next;
        }

        public int getAnticipateCost(Point end) {
            return Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
        }

        @Override
        public String toString() {
            return x + "-" + y + "-" + cost;
        }

        @Override
        public int compareTo(Point o) {
            return this.priority - o.priority;
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
            MAP[i + 2][7].able = 8;
            MAP[2][i + 1].able = 8;
            MAP[7][i + 1].able = 8;
        }
    }

    public static void getPath(Point start, Point end) {
        List<Point> list = new ArrayList<>();
        while (true) {
            end.able = 0;
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

    public static Point dijkstra(Point start, Point end) {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.x == end.x && current.y == end.y) {
                return end;
            }
            current.able = 2;
            List<Point> nextList = current.getNext(start, end);
            for (Point next : nextList) {
                int cost = current.cost + current.getAnticipateCost(next);
                if (next.able == 1 || cost < next.cost) {
                    next.cost = cost;
                    next.priority = cost ;
                    next.last = current;
                    queue.add(next);
                }
            }
            print();
        }
        return null;
    }
    public static Point bestFirst(Point start, Point end) {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.x == end.x && current.y == end.y) {
                return end;
            }
            current.able = 2;
            List<Point> nextList = current.getNext(start, end);
            for (Point next : nextList) {
                if (next.last == null && next.able != 8) {
                    next.foreCast = next.getAnticipateCost(end);
                    next.priority = next.foreCast ;
                    next.last = current;
                    queue.add(next);
                }
            }
            print();
        }
        return null;
    }

    public static void main(String[] args) {
        initializationGraph();
        Point start = MAP[3][3];
        Point end = MAP[7][9];
        LocalTime startTime = LocalTime.now();
        Point able = dijkstra(start, end);
        Duration duration = Duration.between(startTime, LocalTime.now());
        System.out.println(duration.toMillis());
        if (able == end) {
            getPath(start, end);
        }
        print();
    }
}

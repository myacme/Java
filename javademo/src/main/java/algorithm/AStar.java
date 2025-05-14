package algorithm;


import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A*算法
 *
 * @author ljx
 * @version 1.0.0
 * @create 2024/12/9 上午10:25
 */
public class AStar {

    static class Point implements Comparable<Point> {
        private int x;

        private int y;

        private int cost;

        private int costAll;

        private int able = 1;

        private Point last;

        public Point(int x, int y) {
            this.y = y;
            this.x = x;
        }

        public List<Point> getNext(Point start, Point end) {
            List<Point> next = new ArrayList<>();
            if (this.x > 0 ) {
                next.add(MAP[this.x - 1][this.y]);
            }
            if (this.x < 49) {
                next.add(MAP[this.x + 1][this.y]);
            }
            if (this.y > 0 ) {
                next.add(MAP[this.x][this.y - 1]);
            }
            if (this.y < 49 ) {
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
            return this.costAll - o.costAll;
        }
    }


    private static final Point[][] MAP = new Point[50][50];

    public static void initializationGraph() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                MAP[i][j] = new Point(i, j);
            }
        }
        obstacles(null);
    }

    public static void obstacles(Point point) {
        for (int i = 0; i < 30; i++) {
            MAP[i + 10][40].able = 8;
            MAP[10][i + 10].able = 8;
            MAP[39][i + 10].able = 8;
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
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print(MAP[i][j].able + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Point findPath(Point start, Point end) {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.x == end.x && current.y == end.y) {
                return end;
            }
            current.able = 2;
            List<Point> nextList = current.getNext(start,end);
            for (Point next : nextList ) {
                int cost = current.cost + current.getAnticipateCost(next);
                if (next.able == 1 || cost < next.cost){
                    next.cost = cost;
                    next.costAll = cost + next.getAnticipateCost(end);
                    queue.add(next);
                    next.last = current;
                }
            }
//            print();
        }
        return null;
    }

    public static void main(String[] args) {
        initializationGraph();
        Point start = MAP[0][0];
        Point end = MAP[49][49];
        LocalTime startTime = LocalTime.now();
        Point able = findPath(start, end);
        Duration duration = Duration.between(startTime, LocalTime.now());
        System.out.println(duration.toMillis());
        if (able == end) {
            getPath(start, end);
        }
        print();
    }
}

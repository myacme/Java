package algorithm;


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
public class Algorithm {

    static class Point implements Comparable<Point> {
        public int x;

        public int y;

        public int cost;

        public int able = 1;

        public Point last;

        public Point(int x, int y) {
            this.y = y;
            this.x = x;
        }

        public List<Point> getNext(Point start, Point end) {
            List<Point> next = new ArrayList<>();
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
            next.forEach(point -> {
                point.cost = point.getAnticipateCost(start) + point.getAnticipateCost(end);
                point.last = this;
            });
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
            return this.cost - o.cost;
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
            MAP[2][i+1].able = 0;
            MAP[7][i+1].able = 0;

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

    public static Point findPath(Point start, Point end) {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == end.x && point.y == end.y) {
                return end;
            }
            point.able = 2;

            List<Point> next = point.getNext(start,end);
            queue.addAll(next);
        }
        return null;
    }

    public static void main(String[] args) {
        initializationGraph();
        Point start = MAP[6][5];
        Point end = MAP[9][9];
        Point able = findPath(start, end);
        if (able == end) {
            getPath(start, end);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(MAP[i][j].able + " ");
            }
            System.out.println();
        }
    }
}

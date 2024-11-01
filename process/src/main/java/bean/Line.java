package bean;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/10/8 上午11:30
 */
public class Line {

    private String start;
    private String end;

    public Line(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

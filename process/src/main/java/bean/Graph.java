package bean;


import java.util.List;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/10/8 上午11:33
 */
public class Graph {

    Configure process;

    List<ConfigureNode> nodes;
    List<Line> lines;

    public Graph(List<ConfigureNode> nodes, List<Line> lines) {
        this.nodes = nodes;
        this.lines = lines;
    }

    public Configure getProcess() {
        return process;
    }

    public void setProcess(Configure process) {
        this.process = process;
    }

    public List<ConfigureNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ConfigureNode> nodes) {
        this.nodes = nodes;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}

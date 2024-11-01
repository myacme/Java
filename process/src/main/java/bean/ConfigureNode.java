package bean;


import java.util.List;

/**
 * 审批流程配置实体类
 *
 * @author ljx
 * @since 2021-10-11 10:37:37
 */
public class ConfigureNode extends Configure {

    public ConfigureNode() {
    }

    public ConfigureNode(ConfigureNode configureNode) {
        super(configureNode);
        this.nodeId = configureNode.getNodeId();
        this.nodeName = configureNode.getNodeName();
        this.sign = configureNode.getSign();
        this.parentNodeId = configureNode.getParentNodeId();
        this.parentNode = configureNode.getParentNode();
        this.nextNodeId = configureNode.getNextNodeId();
        this.nextNode = configureNode.getNextNode();
        this.peoples = configureNode.getPeoples();
        this.x = configureNode.getX();
        this.y = configureNode.getY();
    }

    /**
     * 流程节点ID
     */
    private String nodeId;

    /**
     * 流程节点名称
     */
    private String nodeName;

    /**
     * 节点标识
     * 0--开始节点
     * 1--流转节点
     * 2--结束节点
     */
    private int sign;

    /**
     * 上级流程节点ID
     */
    private String parentNodeId;

    /**
     * 上级流程节点
     */
    private ConfigureNode parentNode;

    /**
     * 下级节点id
     */
    private String nextNodeId;

    /**
     * 下级节点
     */
    private ConfigureNode nextNode;

    /**
     * 审批人
     */
    protected List<People> peoples;

    /**
     * x坐标
     */
    private String x;

    /**
     * y坐标
     */
    private String y;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public ConfigureNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(ConfigureNode parentNode) {
        this.parentNode = parentNode;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public ConfigureNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(ConfigureNode nextNode) {
        this.nextNode = nextNode;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
package bean;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/10/8 下午2:44
 */

/*
CREATE TABLE `b_flow_people` (
  `configure_id` varchar(32) NOT NULL,
  `node_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`configure_id`,`node_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
*/
public class People {

    public People() {
    }

    public People(People people) {
        this.configureId = people.getConfigureId();
        this.nodeId = people.getNodeId();
        this.userId = people.getUserId();
        this.userName = people.getUserName();

    }

    public People(String configureId, String nodeId, String userId, String userName) {
        this.configureId = configureId;
        this.nodeId = nodeId;
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * 流程ID
     */
    private String configureId;

    /**
     * 流程节点ID
     */
    private String nodeId;

    String userId;

    private String userName;


    public String getConfigureId() {
        return configureId;
    }

    public void setConfigureId(String configureId) {
        this.configureId = configureId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

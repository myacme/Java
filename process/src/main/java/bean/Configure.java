package bean;


/**
 * 审批流程配置实体类
 *
 * @author ljx
 * @since 2021-10-11 10:37:37
 */
public class Configure {

    public Configure() {
    }

    public Configure(Configure configure) {
        this.configureId = configure.getConfigureId();
        this.configureType = configure.getConfigureType();
        this.configureName = configure.getConfigureName();
    }

    /**
     * 流程ID
     */
    private String configureId;

    /**
     * 流程类型
     */
    private String configureType;

    /**
     * 流程名称
     */
    private String configureName;

    /**
     * 是否有效
     */
    private int valid;

    public String getConfigureId() {
        return configureId;
    }

    public void setConfigureId(String configureId) {
        this.configureId = configureId;
    }

    public String getConfigureType() {
        return configureType;
    }

    public void setConfigureType(String configureType) {
        this.configureType = configureType;
    }

    public String getConfigureName() {
        return configureName;
    }

    public void setConfigureName(String configureName) {
        this.configureName = configureName;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
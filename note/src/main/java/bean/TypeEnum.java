package bean;


import lombok.Getter;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/4/22 下午3:52
 */
@Getter
public enum TypeEnum {
    TABLE("表格"),
    PICTURE("图片"),
    VALUE("值");

    private final String type;

    TypeEnum(String type) {
        this.type = type;
    }

    public boolean equals(String type) {
        return this.type.equals(type);
    }
}

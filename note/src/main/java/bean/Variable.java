package bean;


import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/4/8 16:32
 */
@Data
public class Variable {

    private String key;
    private String valueKey;
    private String type;
    private String sql;
    private String value;
    private String pictureName;
    private ByteArrayOutputStream picture;
    private List<String> head;
    private List<LinkedHashMap<String, Object>> data;

    public void setVariable(Variable value) {
        this.value = value.getValue();
        this.pictureName = value.getPictureName();
        this.picture = value.getPicture();
        this.head = value.getHead();
        this.data = value.getData();
    }
}
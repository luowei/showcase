package showcase.example.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;

/**
 * 演示 Jackson
 *
 * @author zfc827@gmail.com
 */
public class JacksonUtils {

    private final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    /**
     * Jackson 映射器API，用以实现 JSON 和 Object 之间的转换
     */
    private static ObjectMapper objectMapper  = new ObjectMapper();

    /**
     * Object --> JSON
     * 忽略 Object 对象中为 null 的属性
     */
    public void toStringIgnoreEmpty() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 格式化输出 Json Text
     */
    public void formatJsonText() {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    /**
     * 设置转换时间格式
     *
     * @param dateFormat dateFormat
     */
    public void setDateFormat(DateFormat dateFormat) {
        objectMapper.setDateFormat(dateFormat);
    }

    /**
     * Object --> JSON 方法
     *
     * @param object targetObject instance
     * @return jsonText
     */
    public String toJSON(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("toJSON JsonProcessingException：{}", e.getMessage(), e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * JSON --> Object 方法
     *
     * @param json    json字符串
     * @param classes 映射Java Class
     * @param <T> target Object Type
     * @return target Object Type
     */
    public <T> T toObject(String json, Class<T> classes) {
        try {
            return objectMapper.readValue(json, classes);
        } catch (IOException e) {
            logger.error("toObject IOException：{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 构造复杂对象使用 TypeReference 描述
     * @param json jsonText
     * @param typeReference TypeReference instance
     * @param <T> target object type
     * @return target object instance
     */
    public <T> T toObject(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("toObject IOException：{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 使用 JSON 中的值，更新一个 Java 对象
     * @param original original Object
     * @param json json Data
     * @param <T> object type
     * @return target object instance
     */
    public <T> T updateObject(T original, String json) {
        try {
            objectMapper.readerForUpdating(original).readValue(json);
            return original;
        } catch (IOException e) {
            logger.error("updateObject IOException：{}", e.getMessage(), e);
            return null;
        }
    }
}

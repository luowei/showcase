package showcase.example.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ObjectMapper objectMapper;

    public JacksonUtils() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Object --> JSON
     * 忽略 Object 对象中为 null 的属性
     */
    public void toStringIgnoreEmpty() {
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 设置转换时间格式
     *
     * @param dateFormat
     */
    public void setDateFormat(DateFormat dateFormat) {
        this.objectMapper.setDateFormat(dateFormat);
    }

    /**
     * Object --> JSON 方法
     *
     * @param object
     * @return
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
     * @param <T>
     * @return
     */
    public <T> T toObject(String json, Class<T> classes) {
        try {
            return this.objectMapper.readValue(json, classes);
        } catch (IOException e) {
            logger.error("toObject IOException：{}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T toObject(String json, TypeReference<T> typeReference) {
        try {
            return this.objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("toObject IOException：{}", e.getMessage(), e);
            return null;
        }
    }
}

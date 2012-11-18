package showcase.example.json;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import showcase.model.ChildBean;
import showcase.model.ParentBean;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 演示 Jackson
 * @author zfc827@gmail.com
 */
public class JacksonUtilsTest {

    private final Logger logger = LoggerFactory.getLogger(JacksonUtilsTest.class);

    private JacksonUtils jacksonUtils;

    private ChildBean childBean;

    private ParentBean parentBean;

    private Map<String, Object> map = new HashMap<String, Object>();

    private List<Object> list = new ArrayList<Object>(2);

    @Before
    public void initialize() {
        jacksonUtils = new JacksonUtils();

        childBean = new ChildBean(123, new Date(), "childValue");
        parentBean = new ParentBean(321, "parentValue");
        parentBean.setChildBean(childBean);


        map.put("childBean", childBean);
        map.put("parentBean", parentBean);

        list.add(ObjectUtils.cloneIfPossible(childBean));
        list.add(ObjectUtils.cloneIfPossible(childBean));
    }

    /**
     * 简单的 Java Object --> JSON
     */
    @Test
    public void testToJSON() throws Exception {
        logger.debug("childBean convert：{}", jacksonUtils.toJSON(childBean));

        logger.debug("parentBean convert：{}", jacksonUtils.toJSON(parentBean));

        logger.debug("map convert：{}", jacksonUtils.toJSON(map));

        logger.debug("list convert：{}", jacksonUtils.toJSON(list));
    }

    /**
     * JavaBean --> JSON
     * 忽略 Object 中为 null 的属性，在JSON中不输出。
     */
    @Test
    public void testIgnoreEmpty() {
        jacksonUtils.toStringIgnoreEmpty();
        logger.debug(jacksonUtils.toJSON(parentBean));
    }

    /**
     * JavaBean --> JSON
     * 演示对 java.util.Date 类型的格式化
     */
    @Test
    public void testSetDateFormat() {
        jacksonUtils.setDateFormat(new SimpleDateFormat("z yyyy-MM-dd HH:ss:mm"));
        logger.debug(jacksonUtils.toJSON(childBean));
    }

    /**
     * 简单的 JSON --> JavaBean
     */
    @Test
    public void testToObject() {
        String parentBeanJSON = jacksonUtils.toJSON(parentBean);
        logger.debug("before convert json text：{}", parentBeanJSON);

        ParentBean bean = jacksonUtils.toObject(parentBeanJSON, ParentBean.class);
        logger.debug("after convert parentBean：{}", bean.toString());

        String mapJSON = jacksonUtils.toJSON(map);
        logger.debug("before convert json text：{}", parentBeanJSON);

        HashMap hashMap = jacksonUtils.toObject(mapJSON, HashMap.class);
        logger.debug("after convert map：{}", hashMap.toString());

        String listJSON = jacksonUtils.toJSON(list);
        logger.debug("before convert json text：{}", listJSON);

        List<ChildBean> childBeans = jacksonUtils.toObject(listJSON, List.class);
        logger.debug("after convert list：{}", childBeans.toString());

        childBeans = jacksonUtils.toObject(listJSON, new TypeReference<List<ChildBean>>() {});
        logger.debug("after convert list：{}", childBeans.toString());
    }
}

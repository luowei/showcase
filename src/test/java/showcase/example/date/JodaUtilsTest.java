package showcase.example.date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * @author zfc827@gmail.com
 */
public class JodaUtilsTest {

    private final Logger logger = LoggerFactory.getLogger(JodaUtilsTest.class);

    private JodaUtils jodaUtils = new JodaUtils();

    /**
     * 演示 DateTime 对象基本属性
     */
    @Test
    public void testDateTimeProperty() {
        DateTime now = DateTime.now();
        logger.debug("世纪：{}", String.valueOf(now.getCenturyOfEra()));
        logger.debug("年代：{}", String.valueOf(now.getYearOfEra()));
        logger.debug("月：{}", String.valueOf(now.getMonthOfYear()));
        logger.debug("当年第{}周", String.valueOf(now.getWeekOfWeekyear()));
        logger.debug("星期{}", String.valueOf(now.getDayOfWeek()));
        logger.debug("当月第{}天", String.valueOf(now.getDayOfMonth()));
        logger.debug("当年第{}天", String.valueOf(now.getDayOfYear()));
    }

    /**
     * 演示 DateTime 对象的格式化
     */
    @Test
    public void testFormat() {
        logger.debug("format date = {}", jodaUtils.getDateTime().toString("y-M-d, E"));
    }

    /**
     * 演示 DateTime 对象的计算
     */
    @Test
    public void testCalculate() {
        // 计算2012年每个月的第一个周六的日期
        jodaUtils = new JodaUtils(2012, 1, 1, 0, 0, 0);
        for (int i = 1; i <= 12; i++) {
            // 获取每个月第一个星期的周六
            DateTime dateTime = jodaUtils.setMonth(i).dayOfMonth().withMinimumValue().dayOfWeek().setCopy(6);
            // 判断一个星期是否跨月
            if (dateTime.getMonthOfYear() != i) {
                // 如果跨月增加一个星期
                dateTime = dateTime.plusWeeks(1);
            }
            logger.debug("date = {}", dateTime.toString("yyyy-MM-dd"));
        }
    }

    /**
     * DateTime 与 java.util.Date / java.util.Calendar
     */
    @Test
    public void testConvert() {
        DateTime dateTime = jodaUtils.getDateTime();
        java.util.Date date = dateTime.toDate();
        logger.debug("Date : {}", date.toString());
        java.util.Calendar calendar = dateTime.toCalendar(Locale.CHINESE);
        logger.debug("Calendar : {}", calendar.toString());
    }
}

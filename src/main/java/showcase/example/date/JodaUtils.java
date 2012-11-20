package showcase.example.date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 演示 Joda
 *
 * @author zfc827@gmail.com
 */
public class JodaUtils {

    private final Logger logger = LoggerFactory.getLogger(JodaUtils.class);

    /**
     * joda 封装的日期对象，类似 java.util.Date
     */
    private DateTime dateTime;

    public JodaUtils() {
        this.dateTime = DateTime.now();
    }

    /**
     * 创建一个指定 年月日/时分秒 DateTime 对象。
     *
     * @param year
     * @param month
     * @param day
     * @param hours
     * @param minute
     * @param second
     */
    public JodaUtils(int year, int month, int day, int hours, int minute, int second) {
        this.dateTime = new DateTime(year, month, day, hours, minute, second);
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 返回以当前 DateTime 对象表示的时间，减去指定的天份数后的 DateTime 对象
     *
     * @param day
     * @return
     */
    public DateTime minusDays(int day) {
        return dateTime.minusDays(day);
    }

    /**
     * * 返回以当前 DateTime 对象表示的时间，减去指定的月份数后的 DateTime 对象
     *
     * @param month
     * @return
     */
    public DateTime minusMonth(int month) {
        return dateTime.minusMonths(month);
    }

    /**
     * * 返回以当前 DateTime 对象表示的时间，减去指定的年份数后的 DateTime 对象
     *
     * @param year
     * @return
     */
    public DateTime minusYear(int year) {
        return dateTime.minusMonths(year);
    }

    /**
     * 返回当月最大的一天的 DateTime 对象
     *
     * @return
     */
    public DateTime maxDayOfMonth() {
        return dateTime.dayOfMonth().withMaximumValue();
    }

    /**
     * 返回当月最小的一天的 DateTime 对象
     *
     * @return
     */
    public DateTime minDayOfMonth() {
        return dateTime.dayOfMonth().withMinimumValue();
    }

    /**
     * 返回指定年份的 DateTime 对象
     *
     * @param year
     * @return
     */
    public DateTime setYear(int year) {
        return dateTime.year().setCopy(year);
    }

    /**
     * 返回指定月份的 DateTime 对象
     *
     * @param month
     * @return
     */
    public DateTime setMonth(int month) {
        return dateTime.monthOfYear().setCopy(month);
    }

    /**
     * 返回指定星期几的 DateTime 对象
     *
     * @param dayOfWeek
     * @return
     */
    public DateTime setDayOfWeek(int dayOfWeek) {
        return dateTime.dayOfWeek().setCopy(dayOfWeek);
    }

    /**
     * 返回指定当月第几天的 DateTime 对象
     *
     * @param dayOfMonth
     * @return
     */
    public DateTime setDayOfMonth(int dayOfMonth) {
        return dateTime.dayOfMonth().setCopy(dayOfMonth);
    }

    /**
     * 返回指定当年第几天的 DateTime 对象
     *
     * @param dayOfYear
     * @return
     */
    public DateTime setDayOfYear(int dayOfYear) {
        return dateTime.dayOfYear().setCopy(dayOfYear);
    }


}

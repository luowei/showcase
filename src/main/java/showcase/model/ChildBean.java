package showcase.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @author zfc827@gmail.com
 */
public class ChildBean {

    private int identityId;

    private Date date;

    private String value;

    public ChildBean() {
    }

    public ChildBean(int identityId, Date date, String value) {
        this.identityId = identityId;
        this.date = date;
        this.value = value;
    }

    public int getIdentityId() {
        return identityId;
    }

    public void setIdentityId(int identityId) {
        this.identityId = identityId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

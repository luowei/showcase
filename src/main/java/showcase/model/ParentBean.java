package showcase.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zfc827@gmail.com
 */
public class ParentBean {

    private int identityId;

    private String value;

    private ChildBean childBean;

    private String emptyProperty;

    public ParentBean() {
    }

    public ParentBean(int identityId, String value) {
        this.identityId = identityId;
        this.value = value;
    }

    public ParentBean(int identityId, String value, ChildBean childBean) {
        this.identityId = identityId;
        this.value = value;
        this.childBean = childBean;
    }

    public int getIdentityId() {
        return identityId;
    }

    public void setIdentityId(int identityId) {
        this.identityId = identityId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ChildBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public String getEmptyProperty() {
        return emptyProperty;
    }

    public void setEmptyProperty(String emptyProperty) {
        this.emptyProperty = emptyProperty;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

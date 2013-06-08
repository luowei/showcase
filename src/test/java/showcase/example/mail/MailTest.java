package showcase.example.mail;


import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: zfc827
 * Date: 13-6-8
 * Time: 下午3:00
 */
public class MailTest {

    @Test
    public void send() {
        MailUtils mailUtils = new MailUtils();
        mailUtils.setAuthorization(Boolean.TRUE);
        // mailUtils.setFromAddress("发件人邮箱地址");
        // mailUtils.setDestAddresses("收件人邮箱地址");
        // mailUtils.setSmtpServer("SMTP 服务器地址");
        // mailUtils.setDisplayName("发件人显示名称");
        // mailUtils.setUserName("邮箱用户名");
        // mailUtils.setPassword("邮箱密码");
        // mailUtils.setSubject("邮件主题");
        // mailUtils.setContent("邮件正文：这是一封测试邮件……");
        // File attache = new File("附件路径");
        // mailUtils.setAttaches(attache);
        mailUtils.send();
    }
}

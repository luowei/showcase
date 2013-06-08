package showcase.example.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created with IntelliJ IDEA.
 * User: zfc827
 * Date: 13-6-8
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
public class SmtpAuthenticator extends Authenticator {

    public SmtpAuthenticator() {
        super();
    }

    public SmtpAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}

package showcase.example.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: zfc827
 * Date: 13-6-8
 * Time: 下午1:38
 */
public class MailUtils {

    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    private String fromAddress;

    private String[] destAddresses;

    private String displayName;

    private String smtpServer;

    private String userName;

    private String password;

    private String subject;

    private String content;

    private File[] attaches;

    private boolean authorization;

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String[] getDestAddresses() {
        return destAddresses;
    }

    public void setDestAddresses(String... destAddresses) {
        this.destAddresses = destAddresses;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File[] getAttaches() {
        return attaches;
    }

    public void setAttaches(File... attaches) {
        this.attaches = attaches;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }

    public void send(){
        Session session = buildSession();
        Message message = buildMessage(new MimeMessage(session));

        Multipart multipart = buildContent();
        try {
            message.setContent(multipart);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpServer, userName, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            logger.error("send message exception", e);
        }
    }

    private Multipart buildContent() {
        Multipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        try {
            mimeBodyPart.setContent(content, "text/html;charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);
            if (attaches != null) {
                for (int i = 0; i < attaches.length; i++) {
                    mimeBodyPart = new MimeBodyPart();
                    FileDataSource fileDataSource = new FileDataSource(attaches[i]);
                    mimeBodyPart.setDataHandler(new DataHandler(fileDataSource));
                    mimeBodyPart.setFileName(MimeUtility.encodeText(attaches[i].getName()));
                    multipart.addBodyPart(mimeBodyPart);
                }
            }
        } catch (MessagingException e) {
            logger.error("build content exception", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("encoding exception", e);
        }
        return multipart;
    }

    private Message buildMessage(Message message) {
        try {
            Address from = new InternetAddress(fromAddress, displayName);
            message.setFrom(from);

            Address[] dest = new InternetAddress[destAddresses.length];
            for(int i = 0; i < destAddresses.length; i++) {
                dest[i] = new InternetAddress(destAddresses[i]);
            }
            message.setRecipients(Message.RecipientType.TO, dest);
            message.setSubject(subject);
            message.setSentDate(new Date());
        } catch (UnsupportedEncodingException e) {
            logger.error("encoding exception", e);
        } catch (MessagingException e) {
            logger.error("build message exception", e);
        }
        return message;
    }

    private Session buildSession() {
        Session session;Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpServer);

        if(isAuthorization()) {
            properties.put("mail.smtp.auth", true);
            session = Session.getDefaultInstance(properties, new SmtpAuthenticator(userName, password));
        } else {
            properties.put("mail.smtp.auth", false);
            session = Session.getDefaultInstance(properties, null);
        }
        return session;
    }
}

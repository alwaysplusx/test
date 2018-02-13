package org.harmony.test.javaee.mail;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.harmony.test.javaee.OpenEJBConfiguration;
import org.harmony.test.javaee.Property;
import org.harmony.test.javaee.runner.OpenEJBRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author wuxii@foxmail.com
 */
@RunWith(OpenEJBRunner.class)
@OpenEJBConfiguration(properties = { //
        @Property(name = "mail.harmony", value = "new://Resource?type=javax.mail.Session"), //
        @Property(name = "mail.harmony.mail.user", value = "tomeejee@126.com"), //
        @Property(name = "mail.harmony.password", value = "abc123"), //
        @Property(name = "mail.harmony.mail.smtp.auth", value = "true"), //
        @Property(name = "mail.harmony.mail.smtp.port", value = "25"), //
        @Property(name = "mail.harmony.mail.smtp.host", value = "smtp.126.com"), //
        @Property(name = "mail.harmony.mail.transport.protocol", value = "smtp") //
})
public class OpenEJBMailSessionTest {

    @EJB
    private MailBean mailBean;

    @Test
    public void test() {
        mailBean.send("Hello World!", "wuxii@foxmail.com");
    }

    @Stateless
    public static class MailBean {

        @Resource
        private Session session;

        public void send(String content, String to) {
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(session.getProperties().getProperty("mail.user")));
                message.setRecipient(RecipientType.TO, new InternetAddress(to));
                message.setSubject("Test Mail");
                message.setText(content);
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }

}

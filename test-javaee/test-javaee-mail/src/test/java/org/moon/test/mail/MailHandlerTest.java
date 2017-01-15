package org.moon.test.mail;

import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.harmony.test.javaee.mail.MailHelper;
import org.junit.Test;

public class MailHandlerTest {

    @Test
    public void testSendMail() throws Exception {
        Session session = MailHelper.getMailSession();
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("tomeejee@126.com"));
        message.setRecipient(RecipientType.TO, new InternetAddress("wuxii@foxmail.com"));
        message.setSubject("Test Mail With Attachment");
        message.setSentDate(new Date());
        // 添加附件
        Multipart multipart = new MimeMultipart();
        MimeBodyPart attachmentsPart = new MimeBodyPart();
        attachmentsPart.setFileName("log4j.properties");
        attachmentsPart.setDataHandler(new DataHandler(new FileDataSource("src/main/resources/log4j.properties")));
        multipart.addBodyPart(attachmentsPart);
        message.setContent(multipart);

        Transport.send(message);
    }

    public static void main(String[] args) {
        Session session = MailHelper.getMailSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("tomeejee@126.com"));
            message.setRecipient(RecipientType.TO, new InternetAddress("wuxii@foxmail.com"));
            message.setSubject("Test Mail");
            message.setText("Hello World!");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

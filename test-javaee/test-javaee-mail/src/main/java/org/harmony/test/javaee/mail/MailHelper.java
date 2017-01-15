package org.harmony.test.javaee.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * @author wuxii@foxmail.com
 */
public class MailHelper {

    private static final Properties props = new Properties();

    static {
        try {
            props.load(ClassLoader.getSystemResourceAsStream("mail.properties"));
        } catch (IOException e) {
        }
    }

    public static Session getMailSession() {
        return Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(//
                        props.getProperty("mail.username"), //
                        props.getProperty("mail.password")//
                );
            }
        });
    }

}

package org.moon.test.mail;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.moon.test.mail.impl.MailHandlerImpl;

public class MailHandlerTest {

    @Test
    @Ignore
    public void testSendTextMain() {
        MailHandler mailHandler = new MailHandlerImpl();
        SimpleMail mail = new SimpleMail("tomeejee@126.com", new String[] { "870757543@qq.com" }, "Come On", "I Support You");
        mail.setAttachFileNames(new String[] { "src/test/resources/material.pdf" });
        assertEquals(true, mailHandler.sendTextMain(mail));
    }

}

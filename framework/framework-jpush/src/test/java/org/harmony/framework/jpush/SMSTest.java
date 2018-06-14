package org.harmony.framework.jpush;

import org.junit.Test;

import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;

/**
 * @author wuxii@foxmail.com
 */
public class SMSTest {

	public static void main(String[] args) throws Exception {
		new SMSTest().test();
	}

	SMSClient client = new SMSClient("secret", "key");

	@Test
	public void test() throws Exception {

		SMSPayload payload = SMSPayload.newBuilder()//
				.setMobileNumber("13000000000")//
				.setTempId(1)//
				// .setCode("123456")//
				// .addTempPara("code", "123456")//
				.build();

		SendSMSResult sr = client.sendSMSCode(payload);

		ValidSMSResult vr = client.sendValidSMSCode(sr.getMessageId(), "codexx");

		System.out.println(sr + ", " + vr);
	}

}

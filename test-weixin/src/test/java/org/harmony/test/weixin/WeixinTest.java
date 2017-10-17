package org.harmony.test.weixin;

import java.security.MessageDigest;

/**
 * @author wuxii@foxmail.com
 */
public class WeixinTest {

    public static void main(String[] args) throws Exception {
        // String signature = "1d837bc26fdee947b2814a32cc761c3083992f46";
        // String echostr = "11341788333970233307";
        String token = "3rkqv67tdsta21";
        String timestamp = "1508252277";
        String nonce = "3860315289";
        String str = timestamp + nonce + token;
        System.out.println(getSha1(str));
    }

    public static String getSha1(String str) throws Exception {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
        mdTemp.update(str.getBytes("UTF-8"));
        byte[] md = mdTemp.digest();
        int j = md.length;
        char[] buf = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(buf);
    }
}

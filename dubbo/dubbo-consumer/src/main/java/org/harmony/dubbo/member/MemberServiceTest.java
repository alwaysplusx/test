package org.harmony.dubbo.member;

import org.harmony.dubbo.api.Member;
import org.harmony.dubbo.api.MemberService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wuxii@foxmail.com
 */
public class MemberServiceTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/dubbo-consumer.xml");
        context.start();

        MemberService memberService = context.getBean("memberService", MemberService.class);
        Member member = memberService.findMember("wuxii");

        System.out.println(member);

        context.close();
    }

}

package org.harmony.dubbo.member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.harmony.dubbo.api.Member;
import org.harmony.dubbo.api.MemberService;

/**
 * @author wuxii@foxmail.com
 */
public class MemberServiceImpl implements MemberService {

    private List<Member> members = Arrays.asList(new Member("wuxii", 18), new Member("david", 10), //
            new Member("mary", 22), new Member("linda", 30), new Member("kent", 7));

    @Override
    public Member findMember(String name) {
        Optional<Member> member = members.stream().filter(e -> name.equals(e.getName())).findFirst();
        return member.isPresent() ? member.get() : null;
    }

}

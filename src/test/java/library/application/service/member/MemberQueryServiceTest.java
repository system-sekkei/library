package library.application.service.member;

import library.LibraryDBTest;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class MemberQueryServiceTest {
    @Autowired
    MemberQueryService memberQueryService;

    @Test
    void 会員を取得できる() {
        MemberNumber memberNumber = new MemberNumber(1);
        Member member = memberQueryService.findMember(memberNumber);

        assertEquals(member.memberNumber().value(), 1);
    }
}
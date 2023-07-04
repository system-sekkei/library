package library.infrastructure.datasource.member;

import library.LibraryDBTest;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * こういうこともできるという例
 * 推奨しているわけではない
 */
@LibraryDBTest
@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    void 会員番号で会員を取得できる() throws Exception {
        MemberNumber targetMember = new MemberNumber(1);
        Member member = memberMapper.selectMember(targetMember);
        assertTrue(member.number().sameValue(targetMember));
    }
}
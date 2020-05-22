package library.infrastructure.datasource.member;

import library.application.repository.MemberRepository;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberStatus;
import org.springframework.stereotype.Repository;

import static library.domain.model.member.MemberStatus.有効;
import static library.domain.model.member.MemberStatus.未登録;

@Repository
public class MemberDataSource implements MemberRepository {
    MemberMapper memberMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public MemberDataSource(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberStatus status(MemberNumber memberNumber) {
        boolean exists = memberMapper.exists(memberNumber);
        if (exists) return 有効;

        return 未登録;
    }
    @Override
    public Member findMember(MemberNumber memberNumber) {
        return memberMapper.selectMember(memberNumber);
    }
}

package library.infrastructure.datasource.member;

import library.application.repository.MemberRepository;
import library.domain.model.member.*;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDataSource implements MemberRepository {
    MemberMapper memberMapper;

    public MemberDataSource(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public void registerMember(Member member) {
        memberMapper.insertMember(member);
    }

    @Override
    public Member findMember(MemberNumber memberNumber) {
        return memberMapper.selectMember(memberNumber);
    }
}

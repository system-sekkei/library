package library.infrastructure.datasource.member;

import library.application.repository.MemberRepository;
import library.domain.model.member.Member;
import library.domain.model.member.Members;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDataSource implements MemberRepository {
    @Override
    public void registerMember(Member member) {

    }

    @Override
    public Members findMembers() {
        return null;
    }
}

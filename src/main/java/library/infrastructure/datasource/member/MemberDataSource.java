package library.infrastructure.datasource.member;

import library.application.repository.MemberRepository;
import library.domain.model.member.*;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDataSource implements MemberRepository {
    @Override
    public void registerMember(Member member) {

    }

    @Override
    public Member findMember(MemberNumber memberNumber) {
        // TODO:
        return new Member(
            new MemberNumber(1),
            new Name("テスト会員"),
            MemberType.大人);
    }

    @Override
    public Members findMembers() {
        return null;
    }
}

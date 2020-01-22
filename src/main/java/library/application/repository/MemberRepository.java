package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;

/**
 * 会員リポジトリ
 */
public interface MemberRepository {
    void registerMember(Member member);

    Member findMember(MemberNumber memberNumber);
}

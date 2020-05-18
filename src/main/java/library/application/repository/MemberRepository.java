package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;

/**
 * 会員リポジトリ
 */
public interface MemberRepository {

    void registerMember(Member member);

    boolean exists(MemberNumber memberNumber);
    Member findMember(MemberNumber memberNumber);
}

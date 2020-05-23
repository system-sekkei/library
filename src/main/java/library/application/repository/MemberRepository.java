package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberStatus;

/**
 * 会員リポジトリ
 */
public interface MemberRepository {
    MemberStatus status(MemberNumber memberNumber);
    Member findBy(MemberNumber memberNumber);
}

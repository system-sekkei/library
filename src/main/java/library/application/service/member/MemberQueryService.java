package library.application.service.member;

import library.application.repository.MemberRepository;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Service;

/**
 * 会員参照サービス
 */
@Service
public class MemberQueryService {
    MemberRepository memberRepository;

    MemberQueryService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 会員を見つける
     */
    public Member findMember(MemberNumber memberNumber) {
        return memberRepository.findMember(memberNumber);
    }

    /**
     * 会員の存在チェック
     */
    public boolean exists(MemberNumber memberNumber) {
        return memberRepository.exists(memberNumber);
    }
}

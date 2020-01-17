package library.domain.model.bookonloan;

import library.domain.model.member.Member;

import java.util.List;

/**
 * 会員の全貸出図書
 */
public class MemberAllBookOnLoans {
    Member member;
    List<BookOnLoan> bookOnLoans;

    public DelayPeriod worstDelayPeriod()  {
        return null;
    }
}

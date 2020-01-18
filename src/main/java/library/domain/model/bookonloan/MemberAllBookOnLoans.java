package library.domain.model.bookonloan;

import library.domain.model.member.Member;
import library.domain.model.member.MemberType;

import java.util.List;

/**
 * 会員の全貸出図書
 */
public class MemberAllBookOnLoans {
    Member member;
    List<BookOnLoan> bookOnLoans;

    public LoanRestrictions loanRestrictions() {
        DelayStatus delayStatus = worstDelayPeriod();
        MemberType memberType = member.memberType();

        if (memberType == MemberType.大人 && delayStatus == DelayStatus.遅延日数３日未満) {
            return LoanRestrictions.貸出５冊まで;
        }

        if (memberType == MemberType.子供) {
            if (delayStatus == DelayStatus.遅延日数３日未満) {
                return LoanRestrictions.貸出７冊まで;
            }

            if (delayStatus == DelayStatus.遅延日数７日未満) {
                return LoanRestrictions.貸出４冊まで;
            }
        }

        return LoanRestrictions.貸出不可;
    }

    public DelayStatus worstDelayPeriod()  {
        return null;
    }
}

package library.domain.model.loan.rule;

import library.domain.model.loan.loan.Loans;
import library.domain.model.loan.loan.DelayPeriod;
import library.domain.model.loan.loan.DelayStatus;
import library.domain.model.member.Member;
import library.domain.model.member.MemberType;
import library.domain.type.date.Date;

/**
 * 会員の全貸出図書
 */
public class MemberAllBookOnLoans {
    Member member;
    Loans loans;

    public MemberAllBookOnLoans(Member member, Loans loans) {
        this.member = member;
        this.loans = loans;
    }

    public CanLoan canBorrowBookToday() {
        LoanRestrictions loanRestrictions = todayLoanRestrictions();
        return loanRestrictions.canLoan(this.loans);
    }

    LoanRestrictions todayLoanRestrictions() {
        Date today = Date.now();
        return loanRestrictions(today);
    }

    LoanRestrictions loanRestrictions(Date date) {
        DelayStatus delayStatus = worstDelayStatus(date);
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

    DelayStatus todayWorstDelayStatus() {
        Date today = Date.now();
        return worstDelayStatus(today);
    }

    DelayStatus worstDelayStatus(Date date) {
        DelayPeriod worstDelayPeriod = loans.worstDelayPeriod(date);

        return worstDelayPeriod.delayStatus();
    }

    public Loans bookOnLoans() {
        return loans;
    }

    public Member member() {
        return member;
    }
}

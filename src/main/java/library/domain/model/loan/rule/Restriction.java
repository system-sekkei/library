package library.domain.model.loan.rule;

import library.domain.model.loan.delay.DelayStatus;
import library.domain.model.loan.Loans;
import library.domain.model.loan.due.Dues;
import library.domain.model.member.Member;
import library.domain.type.date.CurrentDate;

/**
 * 貸出制限
 */
class Restriction {
    Member member;
    Loans loans;
    CurrentDate date;

    Restriction(Member member, Loans loans, CurrentDate date) {
        this.member = member;
        this.loans = loans;
        this.date = date;
    }

    static final RestrictionMap map = new RestrictionMap();

    RestrictionOfLoanbility 貸出可否() {
        DelayStatus delayStatus = new Dues(loans).遅延状態(date);
        DelayOfMember delayOfMember = new DelayOfMember(delayStatus, member.type());
        return map.of(delayOfMember);
    }
}

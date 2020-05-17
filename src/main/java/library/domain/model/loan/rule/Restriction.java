package library.domain.model.loan.rule;

import library.domain.model.loan.loan.DelayStatus;
import library.domain.model.loan.loan.Loans;
import library.domain.model.member.Member;
import library.domain.model.member.MemberType;
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

    static final RestrictionTable table = new RestrictionTable();

    RestrictionOfQuantity ofQuantity() {
        DelayStatus delayStatus = loans.worst(date);
        MemberType memberType = member.type();
        return table.lookup(delayStatus, memberType);
    }
}

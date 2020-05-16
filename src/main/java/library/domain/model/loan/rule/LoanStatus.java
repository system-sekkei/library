package library.domain.model.loan.rule;

import library.domain.model.loan.loan.Loans;
import library.domain.model.loan.loan.DelayStatus;
import library.domain.model.member.Member;
import library.domain.type.date.Date;

/**
 * 貸出状況
 */
public class LoanStatus {
    Member member;
    Loans loans;
    Date date;

    public LoanStatus(Member member, Loans loans, Date date) {
        this.member = member;
        this.loans = loans;
        this.date = date;
    }

    public RestrictionResult shouldRestrict() {
        Restriction restriction = new Restriction(member, loans, date);
        RestrictionOfQuantity restrictionOfQuantity = restriction.ofQuantity();
        return restrictionOfQuantity.shouldRestrict(loans);
    }

    public int count() {
        return loans.count();
    }
}

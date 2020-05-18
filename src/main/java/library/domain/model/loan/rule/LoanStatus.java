package library.domain.model.loan.rule;

import library.domain.model.loan.loan.Loans;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.CurrentDate;

/**
 * 貸出状況
 */
public class LoanStatus {
    Member member;
    Loans loans;
    CurrentDate date;

    public LoanStatus(Member member, Loans loans, CurrentDate date) {
        this.member = member;
        this.loans = loans;
        this.date = date;
    }

    public Loanability shouldRestrict() {
        Restriction restriction = new Restriction(member, loans, date);
        RestrictionOfQuantity restrictionOfQuantity = restriction.ofQuantity();
        return restrictionOfQuantity.shouldRestrict(loans);
    }

    public int count() {
        return loans.count();
    }

    public MemberNumber memberNumber() {
        return member.number();
    }

    public Loans loans() {
        return loans;
    }

    @Override
    public String toString() {
        return "LoanStatus{" +
                "member=" + member +
                ", loans=" + loans +
                ", date=" + date +
                '}';
    }
}

package library.domain.model.loan.rule;

import library.domain.model.delay.DelayStatus;
import library.domain.model.loan.Loans;
import library.domain.model.loan.NumberOfLoans;
import library.domain.model.loan.due.Dues;
import library.domain.model.material.item.Item;
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

    static final RestrictionMap map = new RestrictionMap();

    public LoanStatus(Member member, Loans loans, CurrentDate date) {
        this.member = member;
        this.loans = loans;
        this.date = date;
    }

    public Loanability 貸出可否判定(Item 借りたい所蔵品) {
        RestrictionOfLoanbility restrictionOfLoanbility = 貸出可否();
        return restrictionOfLoanbility.貸出可否判定(member, loans, 借りたい所蔵品);
    }

    public NumberOfLoans count() {
        return loans.冊数();
    }

    public MemberNumber memberNumber() {
        return member.number();
    }

    public Loans loans() {
        return loans;
    }

    RestrictionOfLoanbility 貸出可否() {
        DelayStatus delayStatus = new Dues(loans).遅延状態(date);
        return map.of(delayStatus);
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

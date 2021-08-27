package library.domain.model.loan.rule;

import library.domain.model.loan.Loans;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemWithStatus;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.CurrentDate;

import static library.domain.model.loan.rule.Loanability.貸出中により貸出不可;
import static library.domain.model.material.item.ItemStatus.貸出中;

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

    public Loanability 貸出可否判定(ItemWithStatus 借りたい所蔵品) {
        if (借りたい所蔵品.所蔵品の状態() == 貸出中) return 貸出中により貸出不可;

        Restriction restriction = new Restriction(member, loans, date);
        RestrictionOfLoanbility restrictionOfLoanbility = restriction.貸出可否();
        return restrictionOfLoanbility.貸出可否判定(member, loans, 借りたい所蔵品.所蔵品());
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

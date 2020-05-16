package library.domain.model.loan.loan;

import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.type.date.CurrentDate;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.time.LocalDate;
import java.time.Period;

/**
 * 貸出
 */
public class Loan {
    LoanNumber loanNumber;
    Member member;
    Item item;
    LoanDate loanDate;

    @Deprecated
    Loan() {
    }

    public Loan(LoanNumber loanNumber, Member member, Item item, LoanDate loanDate) {
        this.loanNumber = loanNumber;
        this.member = member;
        this.item = item;
        this.loanDate = loanDate;
    }

    public DaysLate daysLate(CurrentDate date) {
        DueDate dueDate = loanDate.dueDate();
        int delay = Period.between(dueDate.value(), date.value()).getDays();
        return new DaysLate(new Days(delay));
    }


    public Member member() {
        return member;
    }

    public LoanDate loanDate() {
        return loanDate;
    }

    public LoanNumber loanNumber() {
        return loanNumber;
    }

    // TODO 削除：LoanData互換の暫定対応
    public ItemNumber itemNumber() {
        return item.itemNumber();
    }

    public MemberNumber memberNumber() {
        return member.number();
    }
}

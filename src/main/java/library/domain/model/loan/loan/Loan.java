package library.domain.model.loan.loan;

import library.domain.model.book.item.Item;
import library.domain.model.member.Member;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.time.Period;

/**
 * 貸出
 */
public class Loan {
    LoanNumber loanNumber;
    Member member;
    Item itemOnLoan;
    LoanDate loanDate;

    @Deprecated
    Loan() {
    }

    public Loan(LoanNumber loanNumber, Member member, Item itemOnLoan, LoanDate loanDate) {
        this.loanNumber = loanNumber;
        this.member = member;
        this.itemOnLoan = itemOnLoan;
        this.loanDate = loanDate;
    }

    public DueDate dueDate() {
        return loanDate.dueDateWith(LoanPeriod.standard());
    }

    public DelayPeriod todayDelayPeriod() {
        Date today = Date.now();
        return delayPeriod(today);
    }

    public DelayPeriod delayPeriod(Date date) {
        int delay = Period.between(dueDate().value().value(), date.value()).getDays();
        return new DelayPeriod(new Days(delay));
    }

    public Member member() {
        return member;
    }

    public LoanDate loanDate() {
        return loanDate;
    }

    public LoanNumber bookOnLoanId() {
        return loanNumber;
    }
}

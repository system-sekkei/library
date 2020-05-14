package library.domain.model.loan.loan;

import library.domain.model.book.item.ItemOnLoan;
import library.domain.model.member.Member;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.time.Period;

/**
 * 貸出
 */
public class BookOnLoan {
    BookOnLoanId bookOnLoanId;
    Member member;
    ItemOnLoan itemOnLoan;
    LoanDate loanDate;

    @Deprecated
    BookOnLoan() {
    }

    public BookOnLoan(BookOnLoanId bookOnLoanId, Member member, ItemOnLoan itemOnLoan, LoanDate loanDate) {
        this.bookOnLoanId = bookOnLoanId;
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

    public ItemOnLoan holdingOnLoan() {
        return itemOnLoan;
    }

    public LoanDate loanDate() {
        return loanDate;
    }

    public BookOnLoanId bookOnLoanId() {
        return bookOnLoanId;
    }
}

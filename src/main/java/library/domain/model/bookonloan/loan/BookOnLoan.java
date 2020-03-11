package library.domain.model.bookonloan.loan;

import library.domain.model.holding.HoldingOnLoan;
import library.domain.model.member.Member;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.time.Period;

/**
 * 貸出図書
 */
public class BookOnLoan {
    BookOnLoanId bookOnLoanId;
    Member member;
    HoldingOnLoan holdingOnLoan;
    LoanDate loanDate;

    @Deprecated
    BookOnLoan() {
    }

    public BookOnLoan(BookOnLoanId bookOnLoanId, Member member, HoldingOnLoan holdingOnLoan, LoanDate loanDate) {
        this.bookOnLoanId = bookOnLoanId;
        this.member = member;
        this.holdingOnLoan = holdingOnLoan;
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

    public HoldingOnLoan holdingOnLoan() {
        return holdingOnLoan;
    }

    public LoanDate loanDate() {
        return loanDate;
    }

    public BookOnLoanId bookOnLoanId() {
        return bookOnLoanId;
    }
}

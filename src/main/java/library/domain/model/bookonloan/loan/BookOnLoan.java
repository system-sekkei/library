package library.domain.model.bookonloan.loan;

import library.domain.model.bookcollection.BookCollectionOnLoan;
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
    BookCollectionOnLoan bookCollectionOnLoan;
    LoanDate loanDate;

    @Deprecated
    BookOnLoan() {
    }

    public BookOnLoan(BookOnLoanId bookOnLoanId, Member member, BookCollectionOnLoan bookCollectionOnLoan, LoanDate loanDate) {
        this.bookOnLoanId = bookOnLoanId;
        this.member = member;
        this.bookCollectionOnLoan = bookCollectionOnLoan;
        this.loanDate = loanDate;
    }

    public DueDate dueDate() {
        return DueDate.dueDateFrom(loanDate);
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

    public BookCollectionOnLoan bookCollectionOnLoan() {
        return bookCollectionOnLoan;
    }

    public LoanDate loanDate() {
        return loanDate;
    }

    public BookOnLoanId bookOnLoanId() {
        return bookOnLoanId;
    }
}

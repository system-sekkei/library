package library.domain.model.bookonloan;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.member.Member;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.time.Period;

/**
 * 貸出図書
 */
public class BookOnLoan {
    Member member;

    BookCollection bookCollection;

    LoanDate loanDate;

    @Deprecated
    BookOnLoan() {
    }

    public BookOnLoan(Member member, BookCollection bookCollection, LoanDate loanDate) {
        this.member = member;
        this.bookCollection = bookCollection;
        this.loanDate = loanDate;
    }

    public LoanPeriod loanPeriod() {
        return LoanPeriod.loanPeriod(loanDate);
    }

    public DelayPeriod todayDelayPeriod() {
        Date today = Date.now();
        return delayPeriod(today);
    }

    public DelayPeriod delayPeriod(Date date) {
        int delay = Period.between(loanPeriod().value().value(), date.value()).getDays();
        return new DelayPeriod(new Days(delay));
    }

    public Member member() {
        return member;
    }

    public BookCollection bookCollection() {
        return bookCollection;
    }

    public LoanDate loanDate() {
        return loanDate;
    }
}

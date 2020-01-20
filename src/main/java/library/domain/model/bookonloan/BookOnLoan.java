package library.domain.model.bookonloan;

import library.domain.model.bookcollection.BookCollection;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.time.Period;

/**
 * 貸出図書
 */
public class BookOnLoan {
    BookCollection bookCollection;
    LoanDate loanDate;

    public BookOnLoan(BookCollection bookCollection, LoanDate loanDate) {
        this.bookCollection = bookCollection;
        this.loanDate = loanDate;
    }

    public LoanPeriod loanPeriod() {
        return LoanPeriod.loanPeriod(loanDate);
    }

    DelayPeriod delayPeriod() {
        Date today = Date.now();
        int delay = Period.between(loanPeriod().value().value(), today.value()).getDays();
        return new DelayPeriod(new Days(delay));
    }
}

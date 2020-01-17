package library.domain.model.bookonloan;

import library.domain.model.bookcollection.BookCollection;

/**
 * 貸出図書
 */
public class BookOnLoan {
    BookCollection bookCollection;
    LoanDate loanDate;

    public LoanPeriod loanPeriod() {
        return LoanPeriod.loanPeriod(loanDate);
    }

    public DelayPeriod delayPeriod() {
        return null;
    }
}

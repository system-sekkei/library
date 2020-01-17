package library.domain.model.bookonloan;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.member.Member;

/**
 * 貸出図書
 */
public class BookOnLoan {
    Member member;
    BookCollection bookCollection;
    LoanDate loanDate;

    public LoanPeriod loanPeriod() {
        return LoanPeriod.loanPeriod(loanDate);
    }

    public DelayPeriod delayPeriod() {
        return null;
    }
}

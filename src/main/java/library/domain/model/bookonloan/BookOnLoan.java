package library.domain.model.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import java.time.Period;

/**
 * 貸出図書
 */
public class BookOnLoan {
    MemberNumber memberNumber;
    BookCollectionCode bookCollectionCode;
    LoanDate loanDate;

    public BookOnLoan(MemberNumber memberNumber, BookCollectionCode bookCollectionCode, LoanDate loanDate) {
        this.memberNumber = memberNumber;
        this.bookCollectionCode = bookCollectionCode;
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
}

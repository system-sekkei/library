package library.domain.model.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import library.domain.type.date.Days;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Period;

/**
 * 貸出図書
 */
public class BookOnLoan {
    @Valid
    MemberNumber memberNumber;

    @Valid
    BookCollectionCode bookCollectionCode;

    @Valid
    LoanDate loanDate;

    @Deprecated
    BookOnLoan() {
    }

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

    public static BookOnLoan blank() {
        return new BookOnLoan(new MemberNumber(), new BookCollectionCode(), new LoanDate(Date.now()));
    }

    public MemberNumber memberNumber() {
        return memberNumber;
    }

    public BookCollectionCode bookCollectionCode() {
        return bookCollectionCode;
    }

    public LoanDate loanDate() {
        return loanDate;
    }
}

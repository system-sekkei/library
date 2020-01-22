package library.domain.model.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;

/**
 * 貸出図書登録
 */
public class BookOnLoanRegister {
    MemberNumber memberNumber;
    BookCollectionCode bookCollectionCode;
    LoanDate loanDate;

    public BookOnLoanRegister(MemberNumber memberNumber, BookCollectionCode bookCollectionCode, LoanDate loanDate) {
        this.memberNumber = memberNumber;
        this.bookCollectionCode = bookCollectionCode;
        this.loanDate = loanDate;
    }

    public static BookOnLoanRegister blank() {
        return new BookOnLoanRegister(new MemberNumber(), new BookCollectionCode(), new LoanDate(Date.now()));
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

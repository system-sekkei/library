package library.domain.model.bookonloan;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.member.Member;

import javax.validation.Valid;

/**
 * 貸し出されようとしている図書
 */
public class LoaningOfBook {
    @Valid
    Member member;

    @Valid
    BookCollection bookCollection;

    @Valid
    LoanDate loanDate;

    @Deprecated
    LoaningOfBook() {
    }

    public LoaningOfBook(Member member, BookCollection bookCollection, LoanDate loanDate) {
        this.member = member;
        this.bookCollection = bookCollection;
        this.loanDate = loanDate;
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

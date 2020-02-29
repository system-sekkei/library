package library.domain.model.bookonloan.loaning;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.member.Member;

import javax.validation.Valid;

/**
 * 図書の貸出申請
 */
public class BookOnLoanRequest {
    @Valid
    Member member;

    @Valid
    BookCollection bookCollection;

    @Valid
    LoanDate loanDate;

    @Deprecated
    BookOnLoanRequest() {
    }

    public BookOnLoanRequest(Member member, BookCollection bookCollection, LoanDate loanDate) {
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

package library.domain.model.bookonloan.loaning;

import library.domain.model.bookcollection.BookCollectionInStock;
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
    BookCollectionInStock bookCollectionInStock;

    @Valid
    LoanDate loanDate;

    @Deprecated
    BookOnLoanRequest() {
    }

    public BookOnLoanRequest(Member member, BookCollectionInStock bookCollectionInStock, LoanDate loanDate) {
        this.member = member;
        this.bookCollectionInStock = bookCollectionInStock;
        this.loanDate = loanDate;
    }

    public Member member() {
        return member;
    }

    public BookCollectionInStock bookCollectionInStock() {
        return bookCollectionInStock;
    }

    public LoanDate loanDate() {
        return loanDate;
    }
}

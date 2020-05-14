package library.domain.model.loan.rule;

import library.domain.model.loan.loan.LoanDate;
import library.domain.model.book.item.ItemInStock;
import library.domain.model.member.Member;

import javax.validation.Valid;

/**
 * 図書の貸出申請
 */
public class BookOnLoanRequest {
    @Valid
    Member member;

    @Valid
    ItemInStock itemInStock;

    @Valid
    LoanDate loanDate;

    @Deprecated
    BookOnLoanRequest() {
    }

    public BookOnLoanRequest(Member member, ItemInStock itemInStock, LoanDate loanDate) {
        this.member = member;
        this.itemInStock = itemInStock;
        this.loanDate = loanDate;
    }

    public Member member() {
        return member;
    }

    public ItemInStock holdingInStock() {
        return itemInStock;
    }

    public LoanDate loanDate() {
        return loanDate;
    }
}

package library.domain.model.loan.rule;

import library.domain.model.loan.loan.LoanDate;
import library.domain.model.book.item.ItemInStock;
import library.domain.model.member.Member;

import javax.validation.Valid;

/**
 * 貸出申請
 */
public class LoanRequest {
    @Valid
    Member member;

    @Valid
    ItemInStock itemInStock;

    @Valid
    LoanDate loanDate;

    @Deprecated
    LoanRequest() {
    }

    public LoanRequest(Member member, ItemInStock itemInStock, LoanDate loanDate) {
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

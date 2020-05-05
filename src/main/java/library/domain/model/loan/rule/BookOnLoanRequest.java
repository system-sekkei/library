package library.domain.model.loan.rule;

import library.domain.model.loan.loan.LoanDate;
import library.domain.model.item.HoldingInStock;
import library.domain.model.member.Member;

import javax.validation.Valid;

/**
 * 図書の貸出申請
 */
public class BookOnLoanRequest {
    @Valid
    Member member;

    @Valid
    HoldingInStock holdingInStock;

    @Valid
    LoanDate loanDate;

    @Deprecated
    BookOnLoanRequest() {
    }

    public BookOnLoanRequest(Member member, HoldingInStock holdingInStock, LoanDate loanDate) {
        this.member = member;
        this.holdingInStock = holdingInStock;
        this.loanDate = loanDate;
    }

    public Member member() {
        return member;
    }

    public HoldingInStock holdingInStock() {
        return holdingInStock;
    }

    public LoanDate loanDate() {
        return loanDate;
    }
}

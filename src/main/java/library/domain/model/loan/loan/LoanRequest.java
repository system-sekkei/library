package library.domain.model.loan.loan;

import library.domain.model.item.Item;
import library.domain.model.member.Member;

import javax.validation.Valid;

/**
 * 貸出申請
 */
public class LoanRequest {
    @Valid
    Member member;

    @Valid
    Item item;

    @Valid
    LoanDate loanDate;

    @Deprecated
    LoanRequest() {
    }

    public LoanRequest(Member member, Item item, LoanDate loanDate) {
        this.member = member;
        this.item = item;
        this.loanDate = loanDate;
    }

    public Member member() {
        return member;
    }

    public Item item() {
        return item;
    }

    public LoanDate loanDate() {
        return loanDate;
    }
}

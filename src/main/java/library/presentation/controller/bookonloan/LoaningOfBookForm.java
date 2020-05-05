package library.presentation.controller.bookonloan;

import library.domain.model.loan.loan.LoanDate;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;

import javax.validation.Valid;

public class LoaningOfBookForm {

    @Valid
    MemberNumber memberNumber = null;

    @Valid
    ItemNumber itemNumber = null;

    @Valid
    LoanDate loanDate = new LoanDate(Date.now());
}

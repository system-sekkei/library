package library.presentation.controller.bookonloan;

import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.item.ItemNumber;
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

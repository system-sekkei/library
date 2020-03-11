package library.presentation.controller.returnbook;

import library.domain.model.holding.HoldingCode;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.type.date.Date;

import javax.validation.Valid;

public class ReturnBookForm {
    @Valid
    HoldingCode holdingCode = null;

    @Valid
    ReturnDate returnDate = new ReturnDate(Date.now());
}

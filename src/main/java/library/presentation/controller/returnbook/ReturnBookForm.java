package library.presentation.controller.returnbook;

import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.item.ItemNumber;
import library.domain.type.date.Date;

import javax.validation.Valid;

public class ReturnBookForm {
    @Valid
    ItemNumber itemNumber = null;

    @Valid
    ReturnDate returnDate = new ReturnDate(Date.now());
}

package library.presentation.controller.returnbook;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.ReturnDate;
import library.domain.type.date.Date;

import javax.validation.Valid;

public class ReturnBookForm {
    @Valid
    BookCollectionCode bookCollectionCode = null;

    @Valid
    ReturnDate returnDate = new ReturnDate(Date.now());
}

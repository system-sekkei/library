package library.presentation.controller.reservation;

import library.domain.model.book.BookSearchKeyword;

import javax.validation.Valid;

public class BookSearchForm {

    @Valid
    BookSearchKeyword searchKeyword = new BookSearchKeyword("");
}

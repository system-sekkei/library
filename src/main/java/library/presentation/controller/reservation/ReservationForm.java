package library.presentation.controller.reservation;

import library.domain.model.book.BookId;
import library.domain.model.member.MemberNumber;

import javax.validation.Valid;

public class ReservationForm {

    @Valid
    MemberNumber memberNumber = null;

    @Valid
    BookId bookId = null;
}

package library.presentation.controller.reservation;

import library.domain.model.book.bibliography.BookNumber;
import library.domain.model.member.MemberNumber;

import javax.validation.Valid;

public class ReservationForm {

    @Valid
    MemberNumber memberNumber = null;

    @Valid
    BookNumber bookNumber = null;
}

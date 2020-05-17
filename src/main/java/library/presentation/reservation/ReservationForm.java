package library.presentation.reservation;

import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.member.MemberNumber;

import javax.validation.Valid;

public class ReservationForm {

    @Valid
    MemberNumber memberNumber = null;

    @Valid
    BookNumber bookNumber = null;
}

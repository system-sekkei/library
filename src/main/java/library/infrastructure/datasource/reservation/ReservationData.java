package library.infrastructure.datasource.reservation;

import library.domain.model.book.BookId;
import library.domain.model.member.MemberNumber;

public class ReservationData {
    Integer reservationId;
    MemberNumber memberNumber;
    BookId bookId;

    @Deprecated
    ReservationData() {
    }

    public MemberNumber memberNumber() {
        return memberNumber;
    }
}

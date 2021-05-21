package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.book.BookQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.book.bibliography.Keyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.availability.BookAvailability;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.Reservations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class ReservationQueryServiceTest {
    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    BookQueryService bookQueryService;

    @Test
    void 予約を一覧できる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        BookAvailability book = bookQueryService.search(new Keyword("ハンドブック")).asList().get(0);
        Reservation tryingToReserveBook = Reservation.of(member, book.book());
        reservationRecordService.reserve(tryingToReserveBook);

        Reservations reservations = reservationQueryService.reservations();

        assertAll(
                () -> assertEquals("1件", reservations.numberOfReservation().toString()));
    }
}
package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.member.MemberQueryService;
import library.domain.model.book.Book;
import library.domain.model.book.BookSearchKeyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.TryingToReserveBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class ReservedBookQueryServiceTest {
    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    BookQueryService bookQueryService;

    @Test
    void 予約図書一覧を取得することができる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        Book book = bookQueryService.search(new BookSearchKeyword("ハンドブック")).asList().get(0);
        TryingToReserveBook tryingToReserveBook = new TryingToReserveBook(member, book);
        reservationRecordService.registerReservation(tryingToReserveBook);

        Reservations reservations = reservationQueryService.findReservations();

        assertAll(
                () -> assertEquals(1, reservations.numberOfReservation().value()));
    }
}
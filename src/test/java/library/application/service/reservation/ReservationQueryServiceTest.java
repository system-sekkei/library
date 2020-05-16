package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.book.BookQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.Keyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.ReservedBook;
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
    void 予約図書一覧を取得することができる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        Book book = bookQueryService.search(new Keyword("ハンドブック")).asList().get(0);
        Member member1 = member;
        Book book1 = book;
        Reservation tryingToReserveBook = new Reservation(member1, new ReservedBook(book1));
        reservationRecordService.registerReservation(tryingToReserveBook);

        Reservations reservations = reservationQueryService.findReservations();

        assertAll(
                () -> assertEquals(1, reservations.numberOfReservation().value()));
    }

    @Test
    void 会員の現在の貸出予約一覧を取得することができる() {
        Member member = memberQueryService.findMember(new MemberNumber(2));
        Book book = bookQueryService.search(new Keyword("ハンドブック")).asList().get(0);
        Member member1 = member;
        Book book1 = book;
        Reservation tryingToReserveBook = new Reservation(member1, new ReservedBook(book1));
        reservationRecordService.registerReservation(tryingToReserveBook);

        Reservations reservations = reservationQueryService.findReservationsByMember(member);

        assertAll(
                () -> assertEquals(1, reservations.numberOfReservation().value()));
    }
}
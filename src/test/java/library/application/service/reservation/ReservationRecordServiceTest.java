package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.book.BookQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.Keyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.availability.BookAvailability;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservedBook;
import library.infrastructure.datasource.reservation.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
class ReservationRecordServiceTest {
    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    BookQueryService bookQueryService;

    @Autowired
    ReservationMapper reservationMapper;

    @Test
    void 貸出予約を登録することができる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        BookAvailability book = bookQueryService.search(new Keyword("ハンドブック")).asList().get(0);

        Reservation tryingToReserveBook = new Reservation(member, new ReservedBook(book.book()));
        reservationRecordService.registerReservation(tryingToReserveBook);

        List<Reservation> result = reservationMapper.selectAllReservation();

        assertEquals(result.size(), 1);
    }

    @Test
    void 貸出予約を取り消すことができる() {
        Member member = memberQueryService.findMember(new MemberNumber(2));
        BookAvailability book = bookQueryService.search(new Keyword("ハンドブック")).asList().get(0);

        Reservation tryingToReserveBook = new Reservation(member, new ReservedBook(book.book()));
        reservationRecordService.registerReservation(tryingToReserveBook);

        Reservation reservation = reservationMapper.selectAllReservation().get(0);

        reservationRecordService.cancelReservation(reservation);

        List<Reservation> reservations = reservationMapper.selectAllReservation();

        assertTrue(reservations.stream().noneMatch(r -> r.reservationId().value() == reservation.reservationId().value()));
    }
}
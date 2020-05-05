package library.application.coordinator.retention;

import library.LibraryDBTest;
import library.application.repository.CounterRepository;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.BookQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.book.Book;
import library.domain.model.book.BookId;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.reservation.retention.RetentionableReservations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@LibraryDBTest
class RetentionCoordinatorTest {
    @Autowired
    RetentionCoordinator retentionCoordinator;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    CounterRepository counterRepository;

    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    BookQueryService bookQueryService;

    @Test
    void 取置可能な貸出予約図書一覧を出力できる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        Book book = bookQueryService.findBook(new BookId(2));
        Reservation reservation = new Reservation(member, new ReservedBook(book));
        reservationRecordService.registerReservation(reservation);

        RetentionableReservations retentionableReservations = retentionCoordinator.retention();
        Reservation reservation1 = retentionableReservations.asList().get(0);

        assertAll(
                () ->assertTrue(reservation1.reservedBook().book().sameBook(book)),
                () -> assertEquals(1, reservation1.member().memberNumber().value()));
    }
}
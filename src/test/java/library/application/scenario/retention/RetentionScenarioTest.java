package library.application.scenario.retention;

import library.LibraryDBTest;
import library.application.scenario.RetentionScenario;
import library.application.service.member.MemberQueryService;
import library.application.service.book.BookQueryService;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.material.bibliography.Book;
import library.domain.model.material.bibliography.BookNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.request.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@LibraryDBTest
class RetentionScenarioTest {
    @Autowired
    RetentionScenario retentionScenario;

    @Autowired
    ReservationQueryService reservationQueryService;

    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    BookQueryService bookQueryService;

    @Test
    void 取置可能な貸出予約図書一覧を出力できる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        Book book = bookQueryService.findBook(new BookNumber(2));
        Reservation reservation = Reservation.of(member, book);
        reservationRecordService.reserve(reservation);

        // TODO 仕様から再定義
//        Reservations reservations = retentionCoordinator.retention();
//        Reservation reservation1 = reservations.asList().get(0);
//
//        assertAll(
//                () ->assertTrue(reservation1.reservedBook().book().sameBook(book)),
//                () -> assertEquals(1, reservation1.member().memberNumber().value()));
    }
}
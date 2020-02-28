package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.member.MemberQueryService;
import library.domain.model.book.Book;
import library.domain.model.book.BookSearchKeyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.RetentionStatus;
import library.infrastructure.datasource.reservation.ReservationData;
import library.infrastructure.datasource.reservation.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@LibraryDBTest
class ReservationRecordServiceTest {
    @Autowired
    ReservationRecordService reservationRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    BookQueryService bookQueryService;

    @Autowired
    ReservationMapper reservationMapper;

    @Test
    void 貸出予約を登録することができる() {
        Member member = memberQueryService.findMember(new MemberNumber(1));
        Book book = bookQueryService.search(new BookSearchKeyword("ハンドブック")).asList().get(0);

        Reservation reservation = new Reservation(member, book, RetentionStatus.未準備);
        reservationRecordService.registerReservation(reservation);

        ReservationData result = reservationMapper.selectByBookId(book.bookId());

        assertEquals(result.memberNumber().value(), 1);
    }
}
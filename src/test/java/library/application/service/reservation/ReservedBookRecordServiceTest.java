package library.application.service.reservation;

import library.LibraryDBTest;
import library.application.service.member.MemberQueryService;
import library.domain.model.book.Book;
import library.domain.model.book.BookSearchKeyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.reservation.reservation.TryingToReserveBook;
import library.infrastructure.datasource.reservation.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@LibraryDBTest
class ReservedBookRecordServiceTest {
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

        TryingToReserveBook tryingToReserveBook = new TryingToReserveBook(member, book);
        reservationRecordService.registerReservation(tryingToReserveBook);

        List<ReservedBook> result = reservationMapper.selectAllNotRetainedReservation();

        assertEquals(result.size(), 1);
    }
}
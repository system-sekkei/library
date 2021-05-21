package library.application.scenario;

import library.application.service.book.BookQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;
import library.domain.model.book.bibliography.Keyword;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberStatus;
import library.domain.model.reservation.availability.BookAvailabilities;
import library.domain.model.reservation.request.Reservation;
import org.springframework.stereotype.Service;

/**
 * 予約受付シナリオ
 */
@Service
public class ReservationScenario {
    ReservationRecordService reservationRecordService;
    MemberQueryService memberQueryService;
    BookQueryService bookQueryService;

    public ReservationScenario(ReservationRecordService reservationRecordService, MemberQueryService memberQueryService, BookQueryService bookQueryService) {
        this.reservationRecordService = reservationRecordService;
        this.memberQueryService = memberQueryService;
        this.bookQueryService = bookQueryService;
    }

    /**
     * 本を探す
     */
    public BookAvailabilities search(Keyword keyword) {
        return bookQueryService.search(keyword);
    }


    /**
     * 本を見つける
     */
    public Book findBook(BookNumber bookNumber) {
        return bookQueryService.findBook(bookNumber);
    }

    /**
     * 会員番号の有効性を確認する
     */
    public MemberStatus memberStatus(MemberNumber memberNumber) {
        return memberQueryService.status(memberNumber);
    }

    /**
     * 予約を記録する
     */
    public void reserve(Book book, MemberNumber memberNumber) {
        Member member = memberQueryService.findMember(memberNumber);
        Reservation reservation = Reservation.of(member, book);
        reservationRecordService.reserve(reservation);
    }
}

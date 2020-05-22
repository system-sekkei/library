package library.application.coordinator.reservation;

import library.application.service.book.BookQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.member.MemberStatus;
import library.domain.model.reservation.reservation.Reservation;
import org.springframework.stereotype.Service;

/**
 * 予約コーディネータ
 */
@Service
public class ReservationCoordinator {
    ReservationRecordService reservationRecordService;
    MemberQueryService memberQueryService;
    BookQueryService bookQueryService;

    public ReservationCoordinator(ReservationRecordService reservationRecordService, MemberQueryService memberQueryService, BookQueryService bookQueryService) {
        this.reservationRecordService = reservationRecordService;
        this.memberQueryService = memberQueryService;
        this.bookQueryService = bookQueryService;
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
        reservationRecordService.registerReservation(reservation);
    }
}

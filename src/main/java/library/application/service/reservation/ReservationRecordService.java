package library.application.service.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.book.Book;
import library.domain.model.member.Member;
import org.springframework.stereotype.Service;

/**
 * 貸出予約登録サービス
 */
@Service
public class ReservationRecordService {
    ReservationRepository reservationRepository;

    public ReservationRecordService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * 貸出本の予約
     */
    public void registerReservation(Member member, Book book) {
        reservationRepository.registerReservation(member, book);
    }
}

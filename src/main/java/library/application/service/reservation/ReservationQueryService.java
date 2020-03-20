package library.application.service.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.member.Member;
import library.domain.model.reservation.reservation.ReservedBooks;
import org.springframework.stereotype.Service;

/**
 * 貸出予約参照サービス
 */
@Service
public class ReservationQueryService {
    ReservationRepository reservationRepository;

    ReservationQueryService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * 貸出予約一覧
     */
    public ReservedBooks findReservations() {
        return reservationRepository.findReservations();
    }

    /**
     * 会員の現在の貸出予約一覧
     */
    public ReservedBooks findReservationsByMember(Member member) {
        return reservationRepository.findReservationsByMember(member);
    }
}

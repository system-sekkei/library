package library.application.service.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.reservation.Reservations;
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
    public Reservations findReservations() {
        return reservationRepository.findReservations();
    }
}

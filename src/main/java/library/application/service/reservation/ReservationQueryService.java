package library.application.service.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;
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
     * 未準備の予約を一覧する
     */
    public Reservations reservations() {
        return reservationRepository.reservations();
    }

    /**
     * 予約を見つける
     */
    public Reservation reservationOf(ReservationNumber reservationNumber) {
        return reservationRepository.findBy(reservationNumber);
    }
}

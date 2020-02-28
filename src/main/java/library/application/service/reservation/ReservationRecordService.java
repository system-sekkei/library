package library.application.service.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.reservation.Reservation;
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
     * 貸出予約の登録
     */
    public void registerReservation(Reservation reservation) {
        reservationRepository.registerReservation(reservation);
    }
}

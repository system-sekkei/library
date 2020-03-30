package library.application.service.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.reservation.reservation.Reservation;
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
     * 貸出予約を登録する
     */
    public void registerReservation(Reservation tryingToReserveBook) {
        reservationRepository.registerReservation(tryingToReserveBook);
    }

    /**
     * 貸出予約を取消す
     */
    public void cancelReservation(Reservation reservation) {
        reservationRepository.cancelReservation(reservation);
    }
}

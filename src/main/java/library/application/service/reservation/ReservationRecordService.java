package library.application.service.reservation;

import library.application.repository.ReservationRepository;
import library.application.repository.RetentionNotification;
import library.domain.model.reservation.reservation.Reservation;
import org.springframework.stereotype.Service;

/**
 * 貸出予約登録サービス
 */
@Service
public class ReservationRecordService {
    ReservationRepository reservationRepository;
    RetentionNotification retentionNotification;

    public ReservationRecordService(ReservationRepository reservationRepository, RetentionNotification retentionNotification) {
        this.reservationRepository = reservationRepository;
        this.retentionNotification = retentionNotification;
    }

    /**
     * 貸出を予約する
     */
    public void reserve(Reservation tryingToReserveBook) {
        reservationRepository.registerReservation(tryingToReserveBook);
    }

    /**
     * 貸出予約を取消す
     */
    public void cancel(Reservation reservation) {
        reservationRepository.cancelReservation(reservation);
        retentionNotification.notAvailable(reservation);
    }
}

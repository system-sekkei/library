package library.application.service.reservation;

import library.application.service.retention.RetentionNotification;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import org.springframework.stereotype.Service;

/**
 * 予約
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
     * 予約する
     */
    public void reserve(Reservation tryingToReserveMaterial) {
        reservationRepository.reserve(tryingToReserveMaterial);
    }

    /**
     * 取置（予約対応の完了)
     */
    public void retained(ReservationNumber reservation) {
        reservationRepository.retained(reservation);
    }

    /**
     * 予約を取消す
     */
    public void cancel(Reservation reservation) {
        reservationRepository.cancel(reservation);
        retentionNotification.notAvailable(reservation);
    }
}

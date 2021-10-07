package library.application.service.reservation;

import library.application.service.retention.RetentionNotification;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.request.ReservationRequest;
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
    public void reserve(ReservationRequest reservationRequest) {
        reservationRepository.reserve(reservationRequest);
    }

    /**
     * 予約を取消す
     */
    public void cancel(Reservation reservation) {
        reservationRepository.cancel(reservation);
        retentionNotification.notAvailable(reservation);
    }
}

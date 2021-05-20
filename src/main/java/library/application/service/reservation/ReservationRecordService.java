package library.application.service.reservation;

import library.application.service.retention.RetentionNotification;
import library.domain.model.reservation.request.Reservation;
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
     * 予約する
     */
    public void reserve(Reservation tryingToReserveBook) {
        reservationRepository.reserve(tryingToReserveBook);
    }

    /**
     * 予約を取消す
     */
    public void cancel(Reservation reservation) {
        reservationRepository.cancel(reservation);
        retentionNotification.notAvailable(reservation);
    }
}

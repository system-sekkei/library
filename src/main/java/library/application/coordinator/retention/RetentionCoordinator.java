package library.application.coordinator.retention;

import library.application.service.reservation.ReservationQueryService;
import library.application.service.retention.RetentionQueryService;
import library.application.service.retention.RetentionRecordService;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.Retentions;
import org.springframework.stereotype.Service;

/**
 * 取置業務
 */
@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;
    RetentionQueryService retentionQueryService;
    RetentionRecordService retentionRecordService;

    public RetentionCoordinator(ReservationQueryService reservationQueryService, RetentionQueryService retentionQueryService, RetentionRecordService retentionRecordService) {
        this.reservationQueryService = reservationQueryService;
        this.retentionQueryService = retentionQueryService;
        this.retentionRecordService = retentionRecordService;
    }

    /**
     * 予約(取置依頼)を一覧する
     */
    public Reservations reservations() {
        return reservationQueryService.reservations();
    }

    /**
     * 取り置く予約を取得する
     */
    public Reservation reservationOf(ReservationNumber reservationNumber) {
        return reservationQueryService.reservationOf(reservationNumber);
    }

    /**
     * 取り置く
     */
    public void retain(ReservationNumber reservationNumber) {
        Retained retained = new Retained();
        retentionRecordService.registerRetention(retained);
    }
    /**
     * 取置を一覧する
     */
    public Retentions retentions() {
        return retentionQueryService.retentions();
    }
}

package library.application.coordinator.retention;

import library.application.repository.RetentionRepository;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.retention.RetentionQueryService;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.retention.Retentions;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 取置業務
 */
@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;
    RetentionQueryService retentionQueryService;

    public RetentionCoordinator(ReservationQueryService reservationQueryService, RetentionQueryService retentionQueryService) {
        this.reservationQueryService = reservationQueryService;
        this.retentionQueryService = retentionQueryService;
    }

    /**
     * 予約(取置依頼)を一覧する
     */
    public Reservations reservations() {
        return reservationQueryService.findReservations();
    }

    /**
     * 取置を一覧する
     */
    public Retentions retentions() {
        return retentionQueryService.retentions();
    }
}

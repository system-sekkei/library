package library.application.coordinator.retention;

import library.application.service.reservation.ReservationQueryService;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;

    public RetentionCoordinator(ReservationQueryService reservationQueryService) {
        this.reservationQueryService = reservationQueryService;
    }

    /**
     * 取置可能な予約を一覧する
     * TODO: UCでは予約「図書」となっているが、図書ではない
     * TODO: 仕様の再定義から：現在は仮実装
     */
    public Reservations retention() {
        List<Reservation> reservations = Collections.emptyList();
        return new Reservations(reservations);
    }
}

package library.application.coordinator.retention;

import library.application.service.reservation.ReservationQueryService;
import library.application.service.retention.RetentionQueryService;
import library.domain.model.reservation.reservation.ReservedBooks;
import library.domain.model.retention.Retention;
import library.domain.model.retention.RetentionableReservedBooks;
import org.springframework.stereotype.Service;

@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;
    RetentionQueryService retentionQueryService;

    public RetentionCoordinator(ReservationQueryService reservationQueryService, RetentionQueryService retentionQueryService) {
        this.reservationQueryService = reservationQueryService;
        this.retentionQueryService = retentionQueryService;
    }

    /**
     * 貸出予約図書一覧を出力する
     */
    public RetentionableReservedBooks retention() {
        ReservedBooks reservedBooks = reservationQueryService.findReservations();
        Retention retention = retentionQueryService.getRetention();
        return retention.retentionableReservedBooks(reservedBooks);
    }
}

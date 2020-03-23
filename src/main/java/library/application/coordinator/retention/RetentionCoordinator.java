package library.application.coordinator.retention;

import library.application.repository.CounterRepository;
import library.application.service.reservation.ReservationQueryService;
import library.application.service.retention.RetentionQueryService;
import library.domain.model.counter.Counter;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.retention.RetentionableReservedBooks;
import org.springframework.stereotype.Service;

@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;
    RetentionQueryService retentionQueryService;
    CounterRepository counterRepository;

    public RetentionCoordinator(ReservationQueryService reservationQueryService, RetentionQueryService retentionQueryService, CounterRepository counterRepository) {
        this.reservationQueryService = reservationQueryService;
        this.retentionQueryService = retentionQueryService;
        this.counterRepository = counterRepository;
    }

    /**
     * 取置可能な貸出予約図書一覧を出力する
     */
    public RetentionableReservedBooks retention() {
        Reservations reservations = reservationQueryService.findReservations();
        Counter counter = retentionQueryService.counter(reservations);
        return counter.retentionableReservedBooks(reservations);
    }

    /**
     * 貸出予約
     */
    public Reservations findReservedBooks() {
        // TODO:
        return null;
    }
}

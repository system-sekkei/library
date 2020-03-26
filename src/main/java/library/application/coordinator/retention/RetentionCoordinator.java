package library.application.coordinator.retention;

import library.application.repository.CounterRepository;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.counter.Counter;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.retention.RetentionableReservedBooks;
import org.springframework.stereotype.Service;

@Service
public class RetentionCoordinator {
    ReservationQueryService reservationQueryService;
    CounterRepository counterRepository;

    public RetentionCoordinator(ReservationQueryService reservationQueryService, CounterRepository counterRepository) {
        this.reservationQueryService = reservationQueryService;
        this.counterRepository = counterRepository;
    }

    /**
     * 取置可能な貸出予約図書一覧を出力する
     */
    public RetentionableReservedBooks retention() {
        Reservations reservations = reservationQueryService.findReservations();
        Counter counter = counterRepository.counter(reservations.bookIds());
        return counter.retentionableReservedBooks(reservations);
    }
}

package library.application.coordinator.retention;

import library.application.repository.CounterRepository;
import library.application.service.reservation.ReservationQueryService;
import library.domain.model.reservation.availability.Availability;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.retention.RetentionableReservations;
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
     * 取置可能な予約一覧を取得する
     * TODO: UCでは予約「図書」となっているが、図書ではない
     */
    public RetentionableReservations retention() {
        Reservations reservations = reservationQueryService.findReservations();
        Availability availability = counterRepository.counter(reservations.bookIds());
        return availability.retentionableReservedBooks(reservations);
    }
}

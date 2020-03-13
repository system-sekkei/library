package library.application.repository;

import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.TryingToReserveBook;

public interface ReservationRepository {
    void registerReservation(TryingToReserveBook tryingToReserveBook);

    Reservations findReservations();
}

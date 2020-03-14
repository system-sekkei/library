package library.application.repository;

import library.domain.model.reservation.reservation.ReservedBooks;
import library.domain.model.reservation.reservation.TryingToReserveBook;

public interface ReservationRepository {
    void registerReservation(TryingToReserveBook tryingToReserveBook);

    ReservedBooks findReservations();
}

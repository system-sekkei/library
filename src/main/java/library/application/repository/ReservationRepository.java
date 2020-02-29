package library.application.repository;

import library.domain.model.reservation.TryingToReserveBook;

public interface ReservationRepository {
    void registerReservation(TryingToReserveBook tryingToReserveBook);
}

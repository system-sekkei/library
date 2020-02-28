package library.application.repository;

import library.domain.model.reservation.Reservation;

public interface ReservationRepository {
    void registerReservation(Reservation reservation);
}

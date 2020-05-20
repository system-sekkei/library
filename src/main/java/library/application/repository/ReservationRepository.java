package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;

public interface ReservationRepository {
    void registerReservation(Reservation tryingToReserveBook);

    Reservations findReservations();
    void cancelReservation(Reservation reservation);
}

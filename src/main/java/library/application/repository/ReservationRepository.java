package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;

public interface ReservationRepository {
    void registerReservation(Reservation tryingToReserveBook);

    Reservations findReservations();

    Reservation reservationOf(ReservationNumber reservationNumber);

    void cancelReservation(Reservation reservation);
}

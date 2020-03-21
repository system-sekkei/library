package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.TryingToReserveBook;

public interface ReservationRepository {
    void registerReservation(TryingToReserveBook tryingToReserveBook);

    Reservations findReservations();

    void cancelReservation(Reservation reservation);

    Reservations findReservationsByMember(Member member);
}

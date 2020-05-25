package library.application.repository;

import library.domain.model.member.Member;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;

/**
 * 予約リポジトリ
 */
public interface ReservationRepository {
    void reserve(Reservation tryingToReserveBook);

    Reservations reservations();

    Reservation findBy(ReservationNumber reservationNumber);

    void cancel(Reservation reservation);
}

package library.application.repository;

import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.Reservations;

/**
 * 予約リポジトリ
 */
public interface ReservationRepository {
    void reserve(Reservation tryingToReserveBook);

    Reservations reservations();

    Reservation findBy(ReservationNumber reservationNumber);

    void cancel(Reservation reservation);
}

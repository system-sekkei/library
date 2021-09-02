package library.application.service.reservation;

import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.request.Reservations;

/**
 * 予約リポジトリ
 */
public interface ReservationRepository {
    void reserve(ReservationRequest reservationRequest);

    Reservations reservations();

    Reservation findBy(ReservationNumber reservationNumber);

    void retained(ReservationNumber reservation);

    void cancel(Reservation reservation);
}

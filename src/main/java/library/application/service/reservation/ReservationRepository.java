package library.application.service.reservation;

import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.*;
import library.domain.model.reservation.request.*;
import library.domain.model.reservation.unprepared.UnpreparedReservations;

/**
 * 予約リポジトリ
 */
public interface ReservationRepository {
    void reserve(ReservationRequest reservationRequest);

    UnpreparedReservations 未準備の予約一覧();

    Reservation findBy(ReservationNumber reservationNumber);

    void cancel(Reservation reservation);

    ReservationStatus status(ReservationNumber reservationNumber);

    Reservations findByMember(MemberNumber memberNumber);
}

package library.application.service.reservation;

import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationStatus;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.request.Reservations;

/**
 * 予約リポジトリ
 */
public interface ReservationRepository {
    void reserve(ReservationRequest reservationRequest);

    Reservations 取置可能な未準備の予約一覧();

    Reservation findBy(ReservationNumber reservationNumber);

    void cancel(Reservation reservation);

    ReservationStatus status(ReservationNumber reservationNumber);

    Reservations findByMember(MemberNumber memberNumber);
}

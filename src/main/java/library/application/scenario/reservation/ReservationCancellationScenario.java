package library.application.scenario.reservation;

import library.application.service.reservation.ReservationQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationNumber;
import org.springframework.stereotype.Service;

/**
 * 予約キャンセルシナリオ
 */
@Service
public class ReservationCancellationScenario {
    ReservationQueryService reservationQueryService;
    ReservationRecordService reservationRecordService;

    public ReservationCancellationScenario(ReservationQueryService reservationQueryService, ReservationRecordService reservationRecordService) {
        this.reservationQueryService = reservationQueryService;
        this.reservationRecordService = reservationRecordService;
    }

    /**
     * 予約の取り消し
     */
    public void cancel(ReservationNumber reservationNumber) {
        Reservation reservation = reservationQueryService.reservationOf(reservationNumber);
        reservationRecordService.cancel(reservation);
    }
}

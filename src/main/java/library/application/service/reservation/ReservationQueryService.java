package library.application.service.reservation;

import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationStatus;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.Reservations;
import library.domain.model.retention.wait.ReservationWithWaitingOrderList;
import org.springframework.stereotype.Service;

/**
 * 予約の参照
 */
@Service
public class ReservationQueryService {
    ReservationRepository reservationRepository;

    ReservationQueryService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * 未準備の予約を一覧する
     */
    public ReservationWithWaitingOrderList 未準備の予約一覧() {
        return reservationRepository.未準備の予約一覧();
    }

    /**
     * 予約を見つける
     */
    public Reservation reservationOf(ReservationNumber reservationNumber) {
        return reservationRepository.findBy(reservationNumber);
    }

    /**
     * 予約の状態を調べる
     */
    public ReservationStatus reservationStatus(ReservationNumber reservationNumber) {
        return reservationRepository.status(reservationNumber);
    }

    /**
     * 会員の予約一覧を取得する
     */
    public Reservations 予約一覧(MemberNumber memberNumber) {
        return reservationRepository.findByMember(memberNumber);
    }
}

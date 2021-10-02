package library.application.service.reservation;

import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationStatus;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.Reservations;
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
     * 取置可能な未準備の予約を一覧する
     */
    public Reservations 取置可能な未準備の予約一覧() {
        return reservationRepository.取置可能な未準備の予約一覧();
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

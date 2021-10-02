package library.infrastructure.datasource.reservation;

import library.application.service.reservation.ReservationRepository;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationStatus;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.request.Reservations;
import library.infrastructure.datasource.member.MemberMapper;
import library.infrastructure.datasource.retention.RetentionMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReservationDatasource implements ReservationRepository {
    ReservationMapper reservationMapper;
    RetentionMapper retentionMapper;
    MemberMapper memberMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ReservationDatasource(ReservationMapper reservationMapper, RetentionMapper retentionMapper, MemberMapper memberMapper) {
        this.reservationMapper = reservationMapper;
        this.retentionMapper = retentionMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public void reserve(ReservationRequest reservationRequest) {
        ReservationNumber reservationNumber = reservationMapper.nextNumber();
        reservationMapper.insertReservation(
                reservationNumber,
                reservationRequest.entryNumber());
        memberMapper.insert予約と会員(reservationRequest.memberNumber(), reservationNumber);
    }

    @Override
    public Reservations 取置可能な未準備の予約一覧() {
        List<Reservation> reservations = reservationMapper.select在庫がある未準備の予約一覧();
        return new Reservations(reservations);
    }

    @Override
    public Reservation findBy(ReservationNumber reservationNumber) {
        return reservationMapper.selectReservation(reservationNumber);
    }

    @Override
    @Transactional
    public void cancel(Reservation reservation) {
        ReservationNumber reservationNumber = reservation.number();
        reservationMapper.cancelReservation(reservationNumber);
        memberMapper.delete予約と会員(reservationNumber);
    }

    @Override
    public ReservationStatus status(ReservationNumber reservationNumber) {
        if (! reservationMapper.exists予約(reservationNumber)) return ReservationStatus.消込済;
        if (retentionMapper.exists準備完了(reservationNumber)) return ReservationStatus.準備完了;
        return ReservationStatus.消込済;
    }

    @Override
    public Reservations findByMember(MemberNumber memberNumber) {
        return new Reservations(reservationMapper.selectReservationsByMember(memberNumber));
    }
}

package library.infrastructure.datasource.reservation;

import library.application.service.reservation.ReservationRepository;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.ReservationStatus;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.ReservationRequest;
import library.domain.model.reservation.request.Reservations;
import library.infrastructure.datasource.retention.RetentionMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReservationDatasource implements ReservationRepository {
    ReservationMapper reservationMapper;
    RetentionMapper retentionMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ReservationDatasource(ReservationMapper reservationMapper, RetentionMapper retentionMapper) {
        this.reservationMapper = reservationMapper;
        this.retentionMapper = retentionMapper;
    }

    @Override
    @Transactional
    public void reserve(ReservationRequest reservationRequest) {
        ReservationNumber reservationNumber = reservationMapper.nextNumber();
        reservationMapper.insertReservation(
                reservationNumber,
                reservationRequest.memberNumber(),
                reservationRequest.entryNumber());
    }

    @Override
    public Reservations reservations() {
        List<Reservation> reservations = reservationMapper.selectAllReservation();
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

package library.infrastructure.datasource.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
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
    public void reserve(Reservation reservation) {
        ReservationNumber reservationNumber = reservationMapper.nextNumber();
        reservationMapper.insertReservation(
                reservationNumber,
                reservation.memberNumber(),
                reservation.bookNumber());

        retentionMapper.insert未準備(reservationNumber);
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
        retentionMapper.delete未準備(reservationNumber);
    }
}

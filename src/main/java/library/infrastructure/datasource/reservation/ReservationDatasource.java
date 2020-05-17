package library.infrastructure.datasource.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.member.Member;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.Reservations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDatasource implements ReservationRepository {
    ReservationMapper reservationMapper;

    public ReservationDatasource(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    @Override
    public void registerReservation(Reservation tryingToReserveBook) {
        Integer reservationId = reservationMapper.newReservationIdentifier();

        System.out.println(tryingToReserveBook);
        reservationMapper.insertReservation(
                reservationId,
                tryingToReserveBook.member().number(),
                tryingToReserveBook.reservedBook().book().bookNumber());
    }

    @Override
    public Reservations findReservations() {
        List<Reservation> reservations = reservationMapper.selectAllReservation();
        return new Reservations(reservations);
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        reservationMapper.insertCancelReservation(reservation.reservationId());
    }

    @Override
    public Reservations findReservationsByMember(Member member) {
        List<Reservation> reservations = reservationMapper.selectReservationsByMemberNumber(member.number());
        return new Reservations(reservations);
    }
}

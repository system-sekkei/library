package library.infrastructure.datasource.reservation;

import library.application.repository.ReservationRepository;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.reservation.ReservedBook;
import library.domain.model.reservation.reservation.TryingToReserveBook;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDatasource implements ReservationRepository {
    ReservationMapper reservationMapper;

    public ReservationDatasource(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    @Override
    public void registerReservation(TryingToReserveBook tryingToReserveBook) {
        Integer reservationId = reservationMapper.newReservationIdentifier();

        reservationMapper.insertReservation(
                reservationId,
                tryingToReserveBook.member().memberNumber(),
                tryingToReserveBook.book().bookId());
    }

    @Override
    public Reservations findReservations() {
        List<ReservedBook> reservedBooks = reservationMapper.selectAllNotRetainedReservation();
        return new Reservations(reservedBooks);
    }
}

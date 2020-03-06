package library.infrastructure.datasource.reservation;

import library.domain.model.book.BookId;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.Reservations;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationMapper {
    Integer newReservationIdentifier();

    void insertReservation(
            @Param("reservationId") Integer reservationId,
            @Param("memberNumber") MemberNumber memberNumber,
            @Param("bookId") BookId bookId);

    ReservationData selectByBookId(@Param("bookId") BookId bookId);

    Reservations findInStockReservations();
}

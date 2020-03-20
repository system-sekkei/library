package library.infrastructure.datasource.reservation;

import library.domain.model.book.BookId;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.reservation.ReservationId;
import library.domain.model.reservation.reservation.ReservedBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    Integer newReservationIdentifier();

    void insertReservation(
            @Param("reservationId") Integer reservationId,
            @Param("memberNumber") MemberNumber memberNumber,
            @Param("bookId") BookId bookId);

    List<ReservedBook> selectAllNotRetainedReservation();

    void insertCancelReservation(@Param("reservationId") ReservationId reservationId);
}

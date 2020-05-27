package library.infrastructure.datasource.reservation;

import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    ReservationNumber nextNumber();

    void insertReservation(
            @Param("reservationNumber") ReservationNumber reservationNumber,
            @Param("memberNumber") MemberNumber memberNumber,
            @Param("bookNumber") BookNumber bookNumber);

    List<Reservation> selectAllReservation();

    Reservation selectReservation(ReservationNumber reservationNumber);

    void cancelReservation(ReservationNumber reservationNumber);
}

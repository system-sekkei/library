package library.infrastructure.datasource.reservation;

import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    ReservationNumber nextNumber();

    void insertReservation(
            @Param("reservationNumber") ReservationNumber reservationNumber,
            @Param("entryNumber") EntryNumber entryNumber);

    List<Reservation> select未準備の予約一覧();

    Reservation selectReservation(ReservationNumber reservationNumber);

    void insert未準備(ReservationNumber reservationNumber);

    void delete未準備(ReservationNumber reservationNumber);

    void cancelReservation(ReservationNumber reservationNumber);

    boolean exists予約(ReservationNumber reservationNumber);
    boolean exist未準備(ReservationNumber reservationNumber);

    List<Reservation> selectReservationsByMember(MemberNumber memberNumber);
}

package library.infrastructure.datasource.reservation;

import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.unprepared.UnpreparedReservation;
import library.domain.model.reservation.wait.WaitingOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    ReservationNumber nextNumber();

    void insertReservation(
            @Param("reservationNumber") ReservationNumber reservationNumber,
            @Param("entryNumber") EntryNumber entryNumber);

    List<UnpreparedReservation> select未準備の予約一覧();
    WaitingOrder select待ち順番(ReservationNumber reservationNumber);

    Reservation selectReservation(ReservationNumber reservationNumber);

    void insert未準備(ReservationNumber reservationNumber);

    void delete未準備(ReservationNumber reservationNumber);

    void cancelReservation(ReservationNumber reservationNumber);

    boolean exists予約(ReservationNumber reservationNumber);
    boolean exist未準備(ReservationNumber reservationNumber);

    List<Reservation> selectReservationsByMember(MemberNumber memberNumber);
}

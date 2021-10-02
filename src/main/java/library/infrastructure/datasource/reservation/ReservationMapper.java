package library.infrastructure.datasource.reservation;

import library.domain.model.loan.LoanNumber;
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

    List<Reservation> select在庫がある未準備の予約一覧();

    Reservation selectReservation(ReservationNumber reservationNumber);

    void cancelReservation(ReservationNumber reservationNumber);

    boolean exists予約(ReservationNumber reservationNumber);

    List<Reservation> selectReservationsByMember(MemberNumber memberNumber);
}

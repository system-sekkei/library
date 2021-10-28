package library.infrastructure.datasource.reservation;

import library.application.service.reservation.ReservationRepository;
import library.domain.model.material.instock.EntryInStock;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.*;
import library.domain.model.reservation.request.*;
import library.domain.model.reservation.wait.ReservationWithWaitingOrder;
import library.domain.model.reservation.wait.ReservationWithWaitingOrderList;
import library.domain.model.reservation.wait.WaitingOrder;
import library.infrastructure.datasource.material.MaterialMapper;
import library.infrastructure.datasource.member.MemberMapper;
import library.infrastructure.datasource.retention.RetentionMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReservationDatasource implements ReservationRepository {
    ReservationMapper reservationMapper;
    RetentionMapper retentionMapper;
    MemberMapper memberMapper;
    MaterialMapper materialMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ReservationDatasource(ReservationMapper reservationMapper, RetentionMapper retentionMapper, MemberMapper memberMapper, MaterialMapper materialMapper) {
        this.reservationMapper = reservationMapper;
        this.retentionMapper = retentionMapper;
        this.memberMapper = memberMapper;
        this.materialMapper = materialMapper;
    }

    @Override
    @Transactional
    public void reserve(ReservationRequest reservationRequest) {
        ReservationNumber reservationNumber = reservationMapper.nextNumber();
        reservationMapper.insertReservation(
                reservationNumber,
                reservationRequest.entryNumber());
        memberMapper.insert予約と会員(reservationRequest.memberNumber(), reservationNumber);

        reservationMapper.insert未準備(reservationNumber);
    }

    @Override
    public ReservationWithWaitingOrderList 未準備の予約一覧() {
        List<Reservation> reservations = reservationMapper.select未準備の予約一覧();

        List<ReservationWithWaitingOrder> 待ち順番と未準備の予約一覧 = reservations.stream().map(reservation -> {
            EntryInStock 所蔵品目と在庫状況 = materialMapper.findEntryInStock(reservation.entryNumber());
            WaitingOrder 待ち順番 = reservationMapper.select待ち順番(reservation.entryNumber(), reservation.reservationNumber());
            return new ReservationWithWaitingOrder(reservation, 所蔵品目と在庫状況, 待ち順番);
        }).collect(Collectors.toList());

        return new ReservationWithWaitingOrderList(待ち順番と未準備の予約一覧);
    }

    @Override
    public Reservation findBy(ReservationNumber reservationNumber) {
        return reservationMapper.selectReservation(reservationNumber);
    }

    @Override
    @Transactional
    public void cancel(Reservation reservation) {
        ReservationNumber reservationNumber = reservation.reservationNumber();
        reservationMapper.cancelReservation(reservationNumber);
        memberMapper.delete予約と会員(reservationNumber);
        reservationMapper.delete未準備(reservationNumber);
    }

    @Override
    public ReservationStatus status(ReservationNumber reservationNumber) {
        if (! reservationMapper.exists予約(reservationNumber)) return ReservationStatus.消込済;
        if (reservationMapper.exist未準備(reservationNumber)) return ReservationStatus.未準備;
        return ReservationStatus.消込済;
    }

    @Override
    public Reservations findByMember(MemberNumber memberNumber) {
        return new Reservations(reservationMapper.selectReservationsByMember(memberNumber));
    }
}

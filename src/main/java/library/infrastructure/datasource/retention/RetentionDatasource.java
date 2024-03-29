package library.infrastructure.datasource.retention;

import library.application.service.retention.RetentionRepository;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationStatus;
import library.domain.model.retention.*;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.member.MemberMapper;
import library.infrastructure.datasource.reservation.ReservationMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RetentionDatasource implements RetentionRepository {
    RetentionMapper retentionMapper;
    ItemMapper itemMapper;
    MemberMapper memberMapper;
    ReservationMapper reservationMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RetentionDatasource(RetentionMapper retentionMapper, ItemMapper itemMapper, MemberMapper memberMapper, ReservationMapper reservationMapper) {
        this.retentionMapper = retentionMapper;
        this.itemMapper = itemMapper;
        this.memberMapper = memberMapper;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public Retained findBy(RetentionNumber retentionNumber) {
        return retentionMapper.select準備完了(retentionNumber);
    }

    @Override
    public Retained findBy(ItemNumber itemNumber) {
        return retentionMapper.select準備完了by所蔵品番号(itemNumber);
    }

    @Override
    public void ensureStatus(ReservationStatus expected) {
        // TODO 実装
    }

    @Override
    @Transactional
    public void retained(Retention retention) {
        RetentionNumber retentionNumber = retentionMapper.nextNumber();
        ItemNumber itemNumber = retention.itemNumber();
        RetainedDate retainedDate = RetainedDate.now();

        // 取置の発生の記録
        retentionMapper.insert取置(retentionNumber, retention.reservationNumber(), itemNumber, retainedDate);
        retentionMapper.insert準備完了(retentionNumber);

        Reservation reservation = reservationMapper.selectReservation(retention.reservationNumber());
        memberMapper.insert取置と会員(reservation.memberNumber(), retentionNumber);

        // 所蔵品の状態
        itemMapper.delete貸出可能(itemNumber);
        itemMapper.insert取置中(itemNumber);

        // 予約の状態
        reservationMapper.delete未準備(retention.reservationNumber());
        memberMapper.delete予約と会員(reservation.reservationNumber());
    }

    @Override
    @Transactional
    public void expired(Retained retained) {
        ItemNumber itemNumber = retained.itemNumber();
        retentionMapper.insert取置解放(retained.retentionNumber());

        // 所蔵品の状態
        itemMapper.delete取置中(itemNumber);
        itemMapper.insert貸出可能(itemNumber);

        // 取置の状態
        retentionMapper.insert取置期限切れ(retained.retentionNumber());
        retentionMapper.delete準備完了(retained.retentionNumber());
        memberMapper.delete取置と会員(retained.retentionNumber());
    }

    @Override
    public RetainedList retentions() {
        List<Retained> list = retentionMapper.selectAll準備完了();
        return new RetainedList(list);
    }
}

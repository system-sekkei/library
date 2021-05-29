package library.infrastructure.datasource.retention;

import library.application.service.retention.RetentionRepository;
import library.domain.model.book.collection.ItemNumber;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.retention.Retained;
import library.domain.model.retention.RetainedDate;
import library.domain.model.retention.RetainedList;
import library.domain.model.retention.Retention;
import library.infrastructure.datasource.item.ItemMapper;
import library.infrastructure.datasource.reservation.ReservationMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RetentionDatasource implements RetentionRepository {
    RetentionMapper retentionMapper;
    ItemMapper itemMapper;
    ReservationMapper reservationMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RetentionDatasource(RetentionMapper retentionMapper, ItemMapper itemMapper, ReservationMapper reservationMapper) {
        this.retentionMapper = retentionMapper;
        this.itemMapper = itemMapper;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public Retained findBy(ItemNumber itemNumber) {
        return retentionMapper.select準備完了(itemNumber);
    }

    @Override
    @Transactional
    public void retained(Retention retention) {
        ReservationNumber reservationNumber = retention.reservationNumber();
        ItemNumber itemNumber = retention.itemNumber();
        RetainedDate retainedDate = RetainedDate.now();

        // 取置の発生の記録
        retentionMapper.insert取置履歴(reservationNumber, itemNumber, retainedDate);
        retentionMapper.insert準備完了(reservationNumber, itemNumber, retainedDate);

        // 蔵書の状態
        itemMapper.delete貸出可能(itemNumber);
        itemMapper.insert取置中(itemNumber);
    }

    @Override
    @Transactional
    public void released(Retained retained) {
        ItemNumber itemNumber = retained.itemNumber();
        retentionMapper.insert取置解放履歴(retained.reservationNumber(), itemNumber);

        // 蔵書の状態
        itemMapper.delete取置中(itemNumber);
        itemMapper.insert貸出可能(itemNumber);

        //　予約の状態
        retentionMapper.delete準備完了(itemNumber);

    }

    @Override
    @Transactional
    public void expired(Retained retained) {
        retentionMapper.insert取置期限切れ履歴(retained.reservationNumber());
    }

    @Override
    public RetainedList retentions() {
        List<Retained> list = retentionMapper.selectAll準備完了();
        return new RetainedList(list);
    }
}

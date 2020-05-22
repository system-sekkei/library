package library.infrastructure.datasource.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.RetainedDate;
import library.domain.model.reservation.retention.RetainedList;
import library.domain.model.reservation.retention.Retention;
import library.infrastructure.datasource.item.ItemMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RetentionDatasource implements RetentionRepository {
    RetentionMapper retentionMapper;
    ItemMapper itemMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RetentionDatasource(RetentionMapper retentionMapper, ItemMapper itemMapper) {
        this.retentionMapper = retentionMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    @Transactional
    public void registerRetention(Retention retention) {
        ReservationNumber reservationNumber = retention.reservationNumber();
        ItemNumber itemNumber = retention.itemNumber();
        RetainedDate retainedDate = RetainedDate.now();

        // 取置の発生の記録
        retentionMapper.insert取置履歴(reservationNumber, itemNumber, retainedDate);

        // 予約の状態
        retentionMapper.insert取置済(reservationNumber, itemNumber, retainedDate);
        retentionMapper.delete取置依頼中(reservationNumber);

        // 蔵書の状態
        itemMapper.delete貸出可能(itemNumber);
        itemMapper.insert取置中(itemNumber);
    }

    @Override
    public Retained findBy(ItemNumber itemNumber) {
        return retentionMapper.select取置済(itemNumber);
    }

    @Override
    public void loaned(ItemNumber itemNumber) {
        retentionMapper.delete取置済(itemNumber);
        itemMapper.delete取置中(itemNumber);
    }

    @Override
    public RetainedList retentions() {
        List<Retained> list = retentionMapper.selectAll取置済();
        return new RetainedList(list);
    }

}

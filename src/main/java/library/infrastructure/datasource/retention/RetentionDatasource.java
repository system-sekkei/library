package library.infrastructure.datasource.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.RetainedDate;
import library.domain.model.reservation.retention.Retention;
import library.domain.model.reservation.retention.Retentions;
import library.infrastructure.datasource.item.ItemMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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

        retentionMapper.insert取置履歴(reservationNumber, itemNumber, RetainedDate.now());
        retentionMapper.delete取置依頼中(reservationNumber);

        itemMapper.delete貸出可能(itemNumber);
        itemMapper.insert取置中(itemNumber);
    }

    // TODO 実装
    @Override
    public Retentions retentions() {
        List<Retained> list = new ArrayList<>();
        return new Retentions(list);
    }
}

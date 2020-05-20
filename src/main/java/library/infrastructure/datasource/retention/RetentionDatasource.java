package library.infrastructure.datasource.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.Retentions;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RetentionDatasource implements RetentionRepository {
    RetentionMapper retentionMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RetentionDatasource(RetentionMapper retentionMapper) {
        this.retentionMapper = retentionMapper;
    }

    @Override
    public void registerRetention(Retained retained) {
        retentionMapper.insertRetainedHolding(
            retained.reservedBook().reservationNumber(),
            retained.holding().itemNumber(),
            retained.retainedDate());
    }

    // TODO 実装
    @Override
    public Retentions retentions() {
        List<Retained> list = new ArrayList<>();
        return new Retentions(list);
    }
}

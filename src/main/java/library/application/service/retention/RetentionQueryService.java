package library.application.service.retention;

import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.RetainedList;
import org.springframework.stereotype.Service;

/**
 * 取置参照サービス
 */
@Service
public class RetentionQueryService {
    RetentionRepository retentionRepository;

    public RetentionQueryService(RetentionRepository retentionRepository) {
        this.retentionRepository = retentionRepository;
    }

    /**
     * 準備完了を一覧する
     */
    public RetainedList retentions() {
        return retentionRepository.retentions();
    }

    /**
     * 準備完了を見つける
     */
    public Retained findBy(ItemNumber itemNumber) {
        return retentionRepository.findBy(itemNumber);
    }
}

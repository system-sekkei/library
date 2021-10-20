package library.application.scenario;

import library.application.service.retention.RetentionRecordService;
import library.domain.model.material.item.ItemNumber;
import org.springframework.stereotype.Service;

/**
 * 取置期限切れシナリオ
 */
@Service
public class RetentionExpireScenario {

    RetentionRecordService retentionRecordService;

    public RetentionExpireScenario(RetentionRecordService retentionRecordService) {
        this.retentionRecordService = retentionRecordService;
    }

    /**
     * 取置の期限切れ
     */
    public void expire(ItemNumber itemNumber) {
        retentionRecordService.releaseAndExpire(itemNumber);
    }
}

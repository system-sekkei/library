package library.application.scenario.retention;

import library.application.service.retention.RetentionRecordService;
import library.domain.model.retention.RetentionNumber;
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
     * 取置を期限切れにする
     */
    public void expire(RetentionNumber retentionNumber) {
        retentionRecordService.releaseAndExpire(retentionNumber);
    }
}

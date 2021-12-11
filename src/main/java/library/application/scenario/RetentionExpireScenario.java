package library.application.scenario;

import library.application.service.retention.RetentionExpiredCheckService;
import library.application.service.retention.RetentionRecordService;
import org.springframework.stereotype.Service;

/**
 * 取置期限切れシナリオ
 */
@Service
public class RetentionExpireScenario {
    RetentionRecordService retentionRecordService;
    RetentionExpiredCheckService retentionExpiredCheckService;

    public RetentionExpireScenario(RetentionRecordService retentionRecordService, RetentionExpiredCheckService retentionExpiredCheckService) {
        this.retentionRecordService = retentionRecordService;
        this.retentionExpiredCheckService = retentionExpiredCheckService;
    }

    /**
     * 期限内に受け取られなかった取置を期限切れにする
     */
    public void expire() {
        retentionExpiredCheckService.期限切れの取置一覧().asList().forEach(retained -> {
            retentionRecordService.releaseAndExpire(retained);
        });
    }
}

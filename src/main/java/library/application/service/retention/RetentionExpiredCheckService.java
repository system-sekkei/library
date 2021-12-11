package library.application.service.retention;

import library.domain.model.loan.Loan;
import library.domain.model.retention.RetainedList;
import org.springframework.stereotype.Service;

/**
 * 取置期限切れ確認サービス
 */
@Service
public class RetentionExpiredCheckService {
    RetentionNotification retentionNotification;
    RetentionQueryService retentionQueryService;

    public RetentionExpiredCheckService(RetentionNotification retentionNotification, RetentionQueryService retentionQueryService) {
        this.retentionNotification = retentionNotification;
        this.retentionQueryService = retentionQueryService;
    }

    /**
     * 取置期限切れを確認する
     */
    public void expiredCheck(Loan loan) {

    }

    /**
     * 期限切れの取置を一覧する
     */
    public RetainedList 期限切れの取置一覧() {
        return retentionQueryService.retentions().期限切れの取置一覧();
    }
}

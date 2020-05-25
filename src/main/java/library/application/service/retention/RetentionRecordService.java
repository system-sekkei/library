package library.application.service.retention;

import library.application.repository.RetentionNotification;
import library.application.repository.RetentionRepository;
import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.Retention;
import org.springframework.stereotype.Service;

/**
 * 取置登録サービス
 */
@Service
public class RetentionRecordService {

    RetentionRepository retentionRepository;
    RetentionNotification retentionNotification;

    public RetentionRecordService(RetentionRepository retentionRepository, RetentionNotification retentionNotification) {
        this.retentionRepository = retentionRepository;
        this.retentionNotification = retentionNotification;
    }

    /**
     * 予約された本を取り置く
     */
    public void retain(Retention retention) {
        retentionRepository.retained(retention);

        Retained retained = retentionRepository.findBy(retention.itemNumber());
        retentionNotification.retained(retained);
    }

    /**
     * 取り置いた蔵書を貸し出す(準備完了を消しこむ)
     */
    public void loan(ItemNumber itemNumber) {
        retentionRepository.release(itemNumber);
        retentionRepository.recordLoan(itemNumber);
    }

    /**
     * 期限切れにする
     */
    public void expire(ItemNumber itemNumber) {
        Retained retained = retentionRepository.findBy(itemNumber);
        retentionRepository.release(itemNumber);
        retentionRepository.recordExpire(retained);
    }

}

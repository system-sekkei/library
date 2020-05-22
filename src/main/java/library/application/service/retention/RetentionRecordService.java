package library.application.service.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.retention.Retention;
import org.springframework.stereotype.Service;

/**
 * 取置登録サービス
 */
@Service
public class RetentionRecordService {

    RetentionRepository retentionRepository;

    public RetentionRecordService(RetentionRepository retentionRepository) {
        this.retentionRepository = retentionRepository;
    }

    /**
     * 予約図書を取り置く
     */
    public void registerRetention(Retention retention) {
        retentionRepository.registerRetention(retention);
    }

    /**
     * 取置を消込 (貸し出した）
     */
    public void loaned(ItemNumber itemNumber) {
        retentionRepository.loaned(itemNumber);
    }
}

package library.application.service.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.retention.RetainedHolding;
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
    public void registerRetention(RetainedHolding retainedHolding) {
        retentionRepository.registerRetention(retainedHolding);
    }
}

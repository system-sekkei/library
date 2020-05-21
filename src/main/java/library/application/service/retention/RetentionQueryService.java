package library.application.service.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.reservation.retention.Retentions;
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
     * 取置を一覧する
     */
    public Retentions retentions() {
        return retentionRepository.retentions();
    }
}

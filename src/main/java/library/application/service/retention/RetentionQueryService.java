package library.application.service.retention;

import library.domain.model.retention.Retention;
import org.springframework.stereotype.Service;

/**
 * 取置参照サービス
 */
@Service
public class RetentionQueryService {

    public Retention getRetention() {
        // TODO: 蔵書
        Retention retention = new Retention();
        return retention;
    }
}

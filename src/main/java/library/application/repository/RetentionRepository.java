package library.application.repository;

import library.domain.model.reservation.retention.Retention;
import library.domain.model.reservation.retention.Retentions;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void registerRetention(Retention retention);
    Retentions retentions();
}

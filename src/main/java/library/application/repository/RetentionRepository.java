package library.application.repository;

import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.Retentions;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void registerRetention(Retained retained);
    Retentions retentions();
}

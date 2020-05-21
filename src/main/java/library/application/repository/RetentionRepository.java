package library.application.repository;

import library.domain.model.reservation.retention.Retention;
import library.domain.model.reservation.retention.RetainedList;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void registerRetention(Retention retention);
    RetainedList retentions();
}

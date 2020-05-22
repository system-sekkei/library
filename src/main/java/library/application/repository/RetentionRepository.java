package library.application.repository;

import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.Retention;
import library.domain.model.reservation.retention.RetainedList;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void registerRetention(Retention retention);
    void loaned(ItemNumber itemNumber);

    RetainedList retentions();

    Retained findBy(ItemNumber itemNumber);
}

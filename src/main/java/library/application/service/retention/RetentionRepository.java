package library.application.service.retention;

import library.domain.model.book.collection.ItemNumber;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.Retention;
import library.domain.model.reservation.retention.RetainedList;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void retained(Retention retention);

    void released(Retained retained);
    void expired(Retained retained);


    RetainedList retentions();

    Retained findBy(ItemNumber itemNumber);
}

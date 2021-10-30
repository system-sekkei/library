package library.application.service.retention;

import library.domain.model.material.item.ItemNumber;
import library.domain.model.retention.Retained;
import library.domain.model.retention.Retention;
import library.domain.model.retention.RetainedList;

/**
 * 取置リポジトリ
 */
public interface RetentionRepository {

    void retained(Retention retention);

    void expired(Retained retained);


    RetainedList retentions();

    Retained findBy(ItemNumber itemNumber);
}

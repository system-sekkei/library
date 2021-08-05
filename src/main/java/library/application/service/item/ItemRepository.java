package library.application.service.item;

import library.domain.model.material.collection.Item;
import library.domain.model.material.collection.ItemNumber;
import library.domain.model.material.collection.ItemStatus;

/**
 * 蔵書リポジトリ
 */
public interface ItemRepository {
    ItemStatus status(ItemNumber itemNumber);
    Item findBy(ItemNumber itemNumber);
}

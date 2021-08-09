package library.application.service.item;

import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;

/**
 * 蔵書リポジトリ
 */
public interface ItemRepository {
    ItemStatus status(ItemNumber itemNumber);
    Item findBy(ItemNumber itemNumber);
}

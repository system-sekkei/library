package library.application.repository;

import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.ItemStatus;

/**
 * 蔵書リポジトリ
 */
public interface ItemRepository {
    ItemStatus status(ItemNumber itemNumber);
    Item findBy(ItemNumber itemNumber);
}

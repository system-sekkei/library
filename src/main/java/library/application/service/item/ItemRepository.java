package library.application.service.item;

import library.domain.model.book.collection.Item;
import library.domain.model.book.collection.ItemNumber;
import library.domain.model.book.collection.ItemStatus;

/**
 * 蔵書リポジトリ
 */
public interface ItemRepository {
    ItemStatus status(ItemNumber itemNumber);
    Item findBy(ItemNumber itemNumber);
}

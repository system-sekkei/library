package library.application.repository;

import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;

/**
 * 蔵書リポジトリ
 */
public interface ItemRepository {
    Item findItemOnLoan(ItemNumber itemNumber);

    Item findItemInStock(ItemNumber itemNumber);
}

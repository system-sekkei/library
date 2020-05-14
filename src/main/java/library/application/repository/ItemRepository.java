package library.application.repository;

import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemInStock;
import library.domain.model.book.item.ItemNumber;

/**
 * 蔵書リポジトリ
 */
public interface ItemRepository {
    Item findItemOnLoan(ItemNumber itemNumber);

    ItemInStock findItemInStock(ItemNumber itemNumber);
}

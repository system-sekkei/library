package library.application.service.holding;

import library.application.repository.ItemRepository;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemNumber;
import org.springframework.stereotype.Service;

/**
 * 蔵書参照サービス
 */
@Service
public class ItemQueryService {
    ItemRepository itemRepository;

    ItemQueryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * 貸出中の蔵書を取得する
     */
    public Item findItemOnLoan(ItemNumber itemNumber) {
        return itemRepository.findItemOnLoan(itemNumber);
    }

    /**
     * 在庫中の蔵書を取得する
     */
    public Item findItemInStock(ItemNumber itemNumber) {
        return itemRepository.findItemInStock(itemNumber);
    }
}

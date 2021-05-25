package library.application.service.item;

import library.domain.model.book.collection.Item;
import library.domain.model.book.collection.ItemNumber;
import library.domain.model.book.collection.ItemStatus;
import org.springframework.stereotype.Service;

/**
 * 蔵書の検索
 */
@Service
public class ItemQueryService {
    ItemRepository itemRepository;

    public ItemQueryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * 蔵書の状態を調べる
     */
    public ItemStatus status(ItemNumber itemNumber) {
        return itemRepository.status(itemNumber);
    }
    /**
     * 蔵書を見つける
     */
    public Item findBy(ItemNumber itemNumber) {
        return itemRepository.findBy(itemNumber);
    }
}

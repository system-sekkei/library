package library.infrastructure.datasource.item;

import library.application.service.item.ItemRepository;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;
import org.springframework.stereotype.Repository;

import static library.domain.model.material.item.ItemStatus.*;

@Repository
public class ItemDatasource implements ItemRepository {
    ItemMapper itemMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ItemDatasource(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public ItemStatus status(ItemNumber itemNumber) {
        if (! itemMapper.exists(itemNumber)) return 未登録;
        if (itemMapper.loanable(itemNumber)) return 在庫中;
        if (itemMapper.loaned(itemNumber)) return 貸出中;
        if (itemMapper.retained(itemNumber)) return 取置中;
        return その他;
    }
    @Override
    public Item findBy(ItemNumber itemNumber) {
        return itemMapper.selectItem(itemNumber);
    }
}

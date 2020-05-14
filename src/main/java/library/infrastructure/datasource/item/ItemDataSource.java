package library.infrastructure.datasource.item;

import library.application.repository.ItemRepository;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemInStock;
import library.domain.model.book.item.ItemNumber;
import library.infrastructure.datasource.bookonloan.BookOnLoanMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDataSource implements ItemRepository {
    ItemMapper itemMapper;
    BookOnLoanMapper bookOnLoanMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ItemDataSource(ItemMapper itemMapper, BookOnLoanMapper bookOnLoanMapper) {
        this.itemMapper = itemMapper;
        this.bookOnLoanMapper = bookOnLoanMapper;
    }

    @Override
    public Item findItemOnLoan(ItemNumber itemNumber) {
        Item item = itemMapper.selectItem(itemNumber);

        if (bookOnLoanMapper.selectByItemNumber(itemNumber).isEmpty()) {
            throw new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", itemNumber));
        }

        return item;
    }

    @Override
    public ItemInStock findItemInStock(ItemNumber itemNumber) {
        Item item = itemMapper.selectItem(itemNumber);

        if (bookOnLoanMapper.selectByItemNumber(itemNumber).isPresent()) {
            throw new IllegalArgumentException(String.format("現在在庫にない蔵書です。蔵書コード：%s", itemNumber));
        }

        return new ItemInStock(item);
    }
}

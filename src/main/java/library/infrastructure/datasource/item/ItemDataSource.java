package library.infrastructure.datasource.item;

import library.application.repository.ItemRepository;
import library.domain.model.book.item.Item;
import library.domain.model.book.item.ItemNumber;
import library.infrastructure.datasource.loan.LoanMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDataSource implements ItemRepository {
    ItemMapper itemMapper;
    LoanMapper loanMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ItemDataSource(ItemMapper itemMapper, LoanMapper loanMapper) {
        this.itemMapper = itemMapper;
        this.loanMapper = loanMapper;
    }

    @Override
    public Item findItemOnLoan(ItemNumber itemNumber) {
        Item item = itemMapper.selectItem(itemNumber);

        if (loanMapper.selectByItemNumber(itemNumber).isEmpty()) {
            throw new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", itemNumber));
        }

        return item;
    }

    @Override
    public Item findItemInStock(ItemNumber itemNumber) {
        Item item = itemMapper.selectItem(itemNumber);

        if (loanMapper.selectByItemNumber(itemNumber).isPresent()) {
            throw new IllegalArgumentException(String.format("現在在庫にない蔵書です。蔵書コード：%s", itemNumber));
        }

        return item;
    }
}

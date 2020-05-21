package library.infrastructure.datasource.item;

import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {
    boolean exists(ItemNumber itemNumber);
    boolean loanable(ItemNumber itemNumber);
    boolean retained(ItemNumber itemNumber);
    boolean loaned(ItemNumber itemNumber);

    Item selectItem(ItemNumber itemNumber);

    void insert貸出可能(ItemNumber itemNumber);
    void delete貸出可能(ItemNumber itemNumber);

    void insert貸出中(ItemNumber itemNumber);
    void delete貸出中(ItemNumber itemNumber);

    void insert取置中(ItemNumber itemNumber);
    void delete取置中(ItemNumber itemNumber);
}

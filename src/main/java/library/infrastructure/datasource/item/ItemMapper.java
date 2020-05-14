package library.infrastructure.datasource.item;

import library.domain.model.book.bibliography.BookId;
import library.domain.model.book.item.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    Item selectItem(@Param("itemNumber") ItemNumber itemNumber);

    List<Item> selectItems(@Param("itemNumbers") List<ItemNumber> itemNumbers);

    List<Item> selectItemsByBookIds(@Param("bookIds") List<BookId> bookIds);

    ItemNumber lockItem(@Param("itemNumber") ItemNumber itemNumber);
}

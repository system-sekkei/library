package library.infrastructure.datasource.item;

import library.domain.model.book.bibliography.BookId;
import library.domain.model.book.item.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    Item selectItem(@Param("itemNumber") ItemNumber itemNumber);
}

package library.infrastructure.datasource.book;

import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.item.bibliography.Keyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> searchBooks(@Param("keyword") Keyword keyword);

    Book findBook(@Param("bookNumber") BookNumber bookNumber);
}

package library.infrastructure.datasource.book;

import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookId;
import library.domain.model.book.bibliography.BookSearchKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> searchBooks(@Param("keyword") BookSearchKeyword bookSearchKeyword);

    Book findBook(@Param("bookId") BookId bookId);
}

package library.infrastructure.datasource.book;

import library.domain.model.book.Book;
import library.domain.model.book.BookSearchKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> searchBooks(@Param("keyword") BookSearchKeyword bookSearchKeyword);

    List<Book> selectAllBooks();
}

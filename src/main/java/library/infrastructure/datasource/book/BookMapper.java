package library.infrastructure.datasource.book;

import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;
import library.domain.model.book.bibliography.Keyword;
import library.domain.model.reservation.loanability.BookLoanability;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    List<BookLoanability> searchBooks(
            @Param("keyword") Keyword keyword,
            @Param("limit") int limit
    );

    Book findBook(BookNumber bookNumber);
}

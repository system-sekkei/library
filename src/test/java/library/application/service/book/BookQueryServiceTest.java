package library.application.service.book;

import library.LibraryDBTest;
import library.domain.model.book.BookSearchKeyword;
import library.domain.model.book.Books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@LibraryDBTest
class BookQueryServiceTest {
    @Autowired
    BookQueryService bookQueryService;

    @Test
    void 本を検索できる() {
        BookSearchKeyword bookSearchKeyword = new BookSearchKeyword("ハンドブック");
        Books books = bookQueryService.search(bookSearchKeyword);

        assertEquals(1, books.size().value());
    }

}
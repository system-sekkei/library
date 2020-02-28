package library.application.service.reservation;

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

        assertAll(
            () -> assertEquals(1, books.size().value()),
            () -> assertEquals(
            "RDRA2.0 ハンドブック: 軽く柔軟で精度の高い要件定義のモデリング手法",
                    books.asList().get(0).title().toString()));
    }

    @Test
    void 検索キーワードがブランクである場合は全件取得する() {
        BookSearchKeyword bookSearchKeyword = new BookSearchKeyword(" ");
        Books books = bookQueryService.search(bookSearchKeyword);

        assertEquals(2, books.size().value());
    }
}
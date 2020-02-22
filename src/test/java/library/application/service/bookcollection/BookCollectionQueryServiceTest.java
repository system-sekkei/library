package library.application.service.bookcollection;

import library.LibraryDBTest;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@LibraryDBTest
class BookCollectionQueryServiceTest {

    @Autowired
    BookCollectionQueryService bookCollectionQueryService;

    @Test
    void 蔵書を取得できる() {
        BookCollectionCode bookCollectionCode = new BookCollectionCode("1-A");
        BookCollection bookCollection = bookCollectionQueryService.findBookCollection(bookCollectionCode);

        assertTrue(bookCollection.bookCollectionCode().sameValue(bookCollectionCode));
    }
}
package library.application.service.bookcollection;

import library.LibraryDBTest;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.BookCollectionOnLoan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
class BookCollectionQueryServiceTest {

    @Autowired
    BookCollectionQueryService bookCollectionQueryService;

    @Test
    void 貸出中の蔵書を取得できる() {
        BookCollectionCode bookCollectionCode = new BookCollectionCode("1-A");
        BookCollectionOnLoan bookCollection = bookCollectionQueryService.findBookCollectionOnLoan(bookCollectionCode);

        assertTrue(bookCollection.bookCollection().bookCollectionCode().sameValue(bookCollectionCode));
    }
}
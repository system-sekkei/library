package library.application.coordinator.returnbook;

import library.LibraryDBTest;
import library.application.service.bookcollection.BookCollectionQueryService;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
class ReturnBookCoordinatorTest {
    @Autowired
    ReturnBookCoordinator returnBookCoordinator;

    @Autowired
    BookCollectionQueryService bookCollectionQueryService;

    @Test
    void 貸出中の蔵書を返却することができる() {
        BookCollectionCode bookCollectionCode = new BookCollectionCode("1-A");
        ReturnBookResult returnBookResult = returnBookCoordinator.returnBook(bookCollectionCode, new ReturnDate(Date.from("2020-02-25")));

        assertTrue(returnBookResult.ok());
    }

    @Test
    void 貸出中ではない蔵書は返却することができない() {
        BookCollectionCode bookCollectionCode = new BookCollectionCode("2-A");
        ReturnBookResult returnBookResult = returnBookCoordinator.returnBook(bookCollectionCode, new ReturnDate(Date.from("2020-02-25")));

        assertTrue(returnBookResult.hasError());
    }
}
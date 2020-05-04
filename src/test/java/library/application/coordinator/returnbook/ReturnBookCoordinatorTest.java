package library.application.coordinator.returnbook;

import library.LibraryDBTest;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.item.ItemNumber;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class ReturnBookCoordinatorTest {
    @Autowired
    ReturnBookCoordinator returnBookCoordinator;

    @Autowired
    BookOnLoanQueryService bookOnLoanQueryService;

    @Test
    void 貸出中の蔵書を返却することができる() {
        ItemNumber itemNumber = new ItemNumber("1-A");
        returnBookCoordinator.returnBook(itemNumber, new ReturnDate(Date.from("2020-02-25")));

        assertThrows(IllegalArgumentException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByItemNumber(new ItemNumber("1-A"));
        });
    }

    @Test
    void 貸出中ではない蔵書は返却することができない() {
        ItemNumber itemNumber = new ItemNumber("2-A");

        assertThrows(IllegalArgumentException.class, () -> {
            returnBookCoordinator.returnBook(itemNumber, new ReturnDate(Date.from("2020-02-25")));
        });
    }
}
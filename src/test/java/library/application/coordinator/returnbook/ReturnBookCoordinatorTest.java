package library.application.coordinator.returnbook;

import library.LibraryDBTest;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.domain.model.holding.HoldingCode;
import library.domain.model.bookonloan.returning.ReturnDate;
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
        HoldingCode holdingCode = new HoldingCode("1-A");
        returnBookCoordinator.returnBook(holdingCode, new ReturnDate(Date.from("2020-02-25")));

        assertThrows(IllegalArgumentException.class, () -> {
            bookOnLoanQueryService.findBookOnLoanByHoldingCode(new HoldingCode("1-A"));
        });
    }

    @Test
    void 貸出中ではない蔵書は返却することができない() {
        HoldingCode holdingCode = new HoldingCode("2-A");

        assertThrows(IllegalArgumentException.class, () -> {
            returnBookCoordinator.returnBook(holdingCode, new ReturnDate(Date.from("2020-02-25")));
        });
    }
}
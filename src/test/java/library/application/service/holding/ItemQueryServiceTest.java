package library.application.service.holding;

import library.LibraryDBTest;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.ItemOnLoan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
class ItemQueryServiceTest {

    @Autowired
    HoldingQueryService holdingQueryService;

    @Test
    void 貸出中の蔵書を取得できる() {
        ItemNumber itemNumber = new ItemNumber("1-A");
        ItemOnLoan itemOnLoan = holdingQueryService.findHoldingOnLoan(itemNumber);

        assertTrue(itemOnLoan.item().itemNumber().sameValue(itemNumber));
    }
}
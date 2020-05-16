package library.application.service.item;

import library.LibraryDBTest;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
class ItemQueryServiceTest {

    @Autowired
    ItemQueryService itemQueryService;

    @Test
    void 貸出中の蔵書を取得できる() {
        ItemNumber itemNumber = new ItemNumber("1-A");
        Item itemOnLoan = itemQueryService.findItemOnLoan(itemNumber);

        assertTrue(itemOnLoan.itemNumber().sameValue(itemNumber));
    }
}
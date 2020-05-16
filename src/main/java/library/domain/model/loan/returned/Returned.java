package library.domain.model.loan.returned;

import library.domain.model.item.ItemNumber;

/**
 * 返却
 * （イベント）
 */
public class Returned {
    ItemNumber itemNumber;
    ReturnDate returnDate;

    public Returned(ItemNumber itemNumber, ReturnDate returnDate) {
        this.itemNumber = itemNumber;
        this.returnDate = returnDate;
    }

    public ItemNumber itemNumber() {
        return itemNumber;
    }
}

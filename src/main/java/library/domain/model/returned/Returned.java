package library.domain.model.returned;

import library.domain.model.material.collection.ItemNumber;

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

    @Deprecated(since = "thymeleaf")
    public ItemNumber itemNumber() {
        return itemNumber;
    }

    @Deprecated(since = "thymeleaf")
    public ReturnDate returnDate() {
        return returnDate;
    }
}

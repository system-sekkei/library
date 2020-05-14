package library.domain.model.book.item;

/**
 * 貸出中の蔵書
 */
public class ItemOnLoan {
    Item item;

    public ItemOnLoan(Item item) {
        this.item = item;
    }

    public ItemStatus holdingStatus() {
        return ItemStatus.貸出中;
    }

    public Item item() {
        return item;
    }
}

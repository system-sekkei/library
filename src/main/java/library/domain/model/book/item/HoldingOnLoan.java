package library.domain.model.book.item;

/**
 * 貸出中の蔵書
 */
public class HoldingOnLoan {
    Item item;

    public HoldingOnLoan(Item item) {
        this.item = item;
    }

    public HoldingStatus holdingStatus() {
        return HoldingStatus.貸出中;
    }

    public Item item() {
        return item;
    }
}

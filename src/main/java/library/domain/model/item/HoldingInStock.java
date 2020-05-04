package library.domain.model.item;

/**
 * 在庫中の蔵書
 */
public class HoldingInStock {
    Item item;

    public HoldingInStock(Item item) {
        this.item = item;
    }

    public HoldingStatus holdingStatus() {
        return HoldingStatus.在庫中;
    }

    public Item holding() {
        return item;
    }
}

package library.domain.model.book.item;

/**
 * 在庫中の蔵書
 */
public class ItemInStock {
    Item item;

    public ItemInStock(Item item) {
        this.item = item;
    }

    public ItemStatus holdingStatus() {
        return ItemStatus.在庫中;
    }

    public Item holding() {
        return item;
    }
}

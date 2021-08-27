package library.domain.model.material.item;

/**
 * 所蔵品とその状態
 */
public class ItemWithStatus {
    Item item;
    ItemStatus status;

    public ItemWithStatus(Item item, ItemStatus status) {
        this.item = item;
        this.status = status;
    }

    public ItemStatus 所蔵品の状態() {
        return status;
    }

    public Item 所蔵品() {
        return item;
    }
}

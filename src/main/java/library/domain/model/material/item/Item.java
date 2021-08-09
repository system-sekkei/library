package library.domain.model.material.item;

import library.domain.model.material.entry.Entry;

/**
 * *所蔵品
 */
public class Item {
    ItemNumber itemNumber;
    Entry entry;
    ItemStatus status; // TODO 現在は未使用：明示的に持つべきか検討する
    ItemType itemType;

    @Deprecated
    Item() {
    }

    public Item(ItemNumber itemNumber, Entry entry) {
        this.itemNumber = itemNumber;
        this.entry = entry;
    }

    public Entry entry() {
        return entry;
    }
    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public String show() {
        return String.format("[%s] %s",
                itemNumber.toString(), entry.show());
    }
    @Override
    public String toString() {
        return "Item{" +
                "itemNumber=" + itemNumber +
                ", entry=" + entry +
                ", status=" + status +
                '}';
    }
}

package library.domain.model.material.item;

import library.domain.model.material.entry.Entry;

/**
 * *所蔵品
 */
public class Item {
    ItemNumber itemNumber;
    Entry entry;

    @Deprecated
    Item() {
    }

    public Item(ItemNumber itemNumber, Entry entry) {
        this.itemNumber = itemNumber;
        this.entry = entry;
    }

    public Entry 所蔵品目() {
        return entry;
    }
    public ItemNumber 所蔵品番号() {
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
                '}';
    }
}

package library.domain.model.material.item;

import library.domain.model.material.entry.Material;

/**
 * *所蔵品
 */
public class Item {
    ItemNumber itemNumber;
    Material material;
    ItemStatus status; // TODO 現在は未使用：明示的に持つべきか検討する
    ItemType itemType;

    @Deprecated
    Item() {
    }

    public Item(ItemNumber itemNumber, Material material) {
        this.itemNumber = itemNumber;
        this.material = material;
    }

    public Material material() {
        return material;
    }
    public ItemNumber itemNumber() {
        return itemNumber;
    }

    public String show() {
        return String.format("[%s] %s",
                itemNumber.toString(), material.show());
    }
    @Override
    public String toString() {
        return "Item{" +
                "itemNumber=" + itemNumber +
                ", material=" + material +
                ", status=" + status +
                '}';
    }
}

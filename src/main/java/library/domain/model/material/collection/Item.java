package library.domain.model.material.collection;

import library.domain.model.material.Material;

/**
 * *蔵書
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

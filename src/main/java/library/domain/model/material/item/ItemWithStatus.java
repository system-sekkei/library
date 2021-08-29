package library.domain.model.material.item;

import static library.domain.model.material.item.ItemLoanability.*;
import static library.domain.model.material.item.ItemStatus.*;

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

    public ItemLoanability 貸出可能かどうか() {
        if (status == 在庫中) return 貸出可能;
        if (status == 貸出中) return 貸出中により貸出不可能;
        if (status == 予約中) return 予約中により貸出不可能;

        return その他の理由で貸出不可能;
    }
}

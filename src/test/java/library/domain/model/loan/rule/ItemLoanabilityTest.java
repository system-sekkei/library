package library.domain.model.loan.rule;

import library.domain.model.material.entry.*;
import library.domain.model.material.item.Item;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;
import library.domain.model.material.item.ItemWithStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 所蔵品の貸出可否のテスト
 */
class ItemLoanabilityTest {

    @Test
    void 予約中の所蔵品の貸出可能状態を取得する() {
        ItemWithStatus 所蔵品 = new ItemWithStatus(null, ItemStatus.予約中); // TODO 所蔵品を使わないので、不要.　設計変更した方が良いのでは？
        ItemLoanability 貸出可能状態 = ItemLoanability.貸出可能かどうか(所蔵品);
        assertEquals(ItemLoanability.予約中により貸出不可能, 貸出可能状態);
    }

    @Test
    void 在庫中の所蔵品の貸出可能状態を取得する() {
        ItemWithStatus 所蔵品 = new ItemWithStatus(null, ItemStatus.在庫中);
        ItemLoanability 貸出可能状態 = ItemLoanability.貸出可能かどうか(所蔵品);
        assertEquals(ItemLoanability.貸出可能, 貸出可能状態);
    }

    @Test
    void 未登録の所蔵品の貸出可能状態を取得する() {
        ItemWithStatus 所蔵品 = new ItemWithStatus(null, ItemStatus.未登録);
        ItemLoanability 貸出可能状態 = ItemLoanability.貸出可能かどうか(所蔵品);
        assertEquals(ItemLoanability.その他の理由で貸出不可能, 貸出可能状態);
    }

    @Test
    void 取置中の所蔵品の貸出可能状態を取得する() {
        ItemWithStatus 所蔵品 = new ItemWithStatus(null, ItemStatus.取置中);
        ItemLoanability 貸出可能状態 = ItemLoanability.貸出可能かどうか(所蔵品);
        assertEquals(ItemLoanability.その他の理由で貸出不可能, 貸出可能状態);
    }

    @Test
    void 貸出中の所蔵品の貸出可能状態を取得する() {
        ItemWithStatus 所蔵品 = new ItemWithStatus(null, ItemStatus.貸出中);
        ItemLoanability 貸出可能状態 = ItemLoanability.貸出可能かどうか(所蔵品);
        assertEquals(ItemLoanability.貸出中により貸出不可能, 貸出可能状態);
    }

    void その他の所蔵品の貸出可能状態を取得する() {
        ItemWithStatus 所蔵品 = new ItemWithStatus(null, ItemStatus.その他);
        ItemLoanability 貸出可能状態 = ItemLoanability.貸出可能かどうか(所蔵品);
        assertEquals(ItemLoanability.その他の理由で貸出不可能, 貸出可能状態);
    }
}
package library.domain.model.loan.rule;

import library.domain.model.material.item.ItemWithStatus;

import static library.domain.model.material.item.ItemStatus.*;

/**
 * 所蔵品の貸出可否
 */
public enum ItemLoanability {
    貸出可能(""),
    貸出中により貸出不可能("貸出中であるため貸し出すことができません。"),
    予約中により貸出不可能("予約中であるため貸し出すことができません。"),
    その他の理由で貸出不可能("貸し出すことができません。");

    String message;

    ItemLoanability(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    public static ItemLoanability 貸出可能かどうか(ItemWithStatus 所蔵品) {
        if (所蔵品.所蔵品の状態() == 在庫中) return 貸出可能;
        if (所蔵品.所蔵品の状態() == 貸出中) return 貸出中により貸出不可能;
        if (所蔵品.所蔵品の状態() == 予約中) return 予約中により貸出不可能;

        return その他の理由で貸出不可能;
    }
}

package library.domain.model.reservation.loanability;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;

/**
 * 本の貸出可否
 */
public class MaterialLoanability {
    Entry entry;
    int loanableItems;

    public String showLoanability() {
        return loanability().show();
    }

    private Loanability loanability() {
        return Loanability.loanability(loanableItems);
    }

    public String describeMaterial() {
        return entry.show();
    }

    public EntryNumber entryNumber() {
        return entry.entryNumber();
    }

    // TODO テスト用：テストを変更して、このメソッドを廃止する
    public Entry entry() {
        return entry;
    }

    @Override
    public String toString() {
        return "MaterialLoanability{" +
                "material=" + entry +
                ", loanable=" + loanableItems +
                '}';
    }
}

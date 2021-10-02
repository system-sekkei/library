package library.domain.model.material.instock;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;

public class EntryInStock {
    Entry entry;
    StockQuantity 在庫数;

    public String showInStock() {
        return 在庫有無().show();
    }

    private InStock 在庫有無() {
        return InStock.在庫有無(在庫数);
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
        return "EntryInStock{" +
                "material=" + entry +
                ", loanable=" + 在庫数 +
                '}';
    }
}

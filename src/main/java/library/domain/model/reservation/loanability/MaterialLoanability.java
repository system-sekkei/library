package library.domain.model.reservation.loanability;

import library.domain.model.material.entry.Material;
import library.domain.model.material.entry.MaterialNumber;

/**
 * 本の貸出可否
 */
public class MaterialLoanability {
    Material material;
    int loanableItems;

    public String showLoanability() {
        return loanability().show();
    }

    private Loanability loanability() {
        return Loanability.loanability(loanableItems);
    }

    public String describeMaterial() {
        return material.show();
    }

    public MaterialNumber materialNumber() {
        return material.materialNumber();
    }

    // TODO テスト用：テストを変更して、このメソッドを廃止する
    public Material material() {
        return material;
    }

    @Override
    public String toString() {
        return "MaterialLoanability{" +
                "material=" + material +
                ", loanable=" + loanableItems +
                '}';
    }
}

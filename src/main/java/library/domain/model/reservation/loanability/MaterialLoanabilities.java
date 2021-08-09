package library.domain.model.reservation.loanability;

import library.domain.model.material.entry.NumberOfEntry;

import java.util.List;

/**
 * 本の一覧と貸出可否
 */
public class MaterialLoanabilities {
    List<MaterialLoanability> list;

    public MaterialLoanabilities(List<MaterialLoanability> list) {
        this.list = list;
    }

    public NumberOfEntry numberOfMaterial() {
        return new NumberOfEntry(list.size());
    }
    public int size() {
        return list.size();
    }

    public List<MaterialLoanability> asList() {
        return list;
    }

    @Override
    public String toString() {
        return "MaterialLoanabilities{" +
                "list=" + list +
                '}';
    }
}

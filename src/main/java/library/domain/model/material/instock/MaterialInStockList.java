package library.domain.model.material.instock;

import library.domain.model.material.entry.NumberOfEntry;

import java.util.List;

/**
 * 本と在庫有無の一覧
 */
public class MaterialInStockList {
    List<MaterialInStock> list;

    public MaterialInStockList(List<MaterialInStock> list) {
        this.list = list;
    }

    public NumberOfEntry numberOfMaterial() {
        return new NumberOfEntry(list.size());
    }
    public int size() {
        return list.size();
    }

    public List<MaterialInStock> asList() {
        return list;
    }

    @Override
    public String toString() {
        return "MaterialInStockList{" +
                "list=" + list +
                '}';
    }
}

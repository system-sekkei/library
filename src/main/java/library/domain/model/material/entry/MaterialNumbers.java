package library.domain.model.material.entry;

import java.util.List;

/**
 * 資料番号のリスト
 */
public class MaterialNumbers {
    List<MaterialNumber> list;

    public MaterialNumbers(List<MaterialNumber> list) {
        this.list = list;
    }

    public List<MaterialNumber> asList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}

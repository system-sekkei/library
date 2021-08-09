package library.domain.model.material.entry;

import java.util.Collections;
import java.util.List;

/**
 * 資料のリスト
 */
public class Materials {
    List<Material> list;

    public Materials(List<Material> list) {
        this.list = list;
    }

    public String count() {
        return new NumberOfMaterial(list.size()).show();
    }
    public int size() {
        return list.size();
    }
    public List<Material> asList() {
        return Collections.unmodifiableList(list);
    }
}

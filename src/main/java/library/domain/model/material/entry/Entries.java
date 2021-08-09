package library.domain.model.material.entry;

import java.util.Collections;
import java.util.List;

/**
 * 所蔵品目のリスト
 */
public class Entries {
    List<Entry> list;

    public Entries(List<Entry> list) {
        this.list = list;
    }

    public String count() {
        return new NumberOfEntry(list.size()).show();
    }
    public int size() {
        return list.size();
    }
    public List<Entry> asList() {
        return Collections.unmodifiableList(list);
    }
}

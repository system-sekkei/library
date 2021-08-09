package library.domain.model.material.entry;

import java.util.List;

/**
 * 資料番号のリスト
 */
public class EntryNumbers {
    List<EntryNumber> list;

    public EntryNumbers(List<EntryNumber> list) {
        this.list = list;
    }

    public List<EntryNumber> asList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}

package library.domain.model.loan;

import library.domain.model.material.entry.EntryType;

import java.util.Collections;
import java.util.List;

/**
 * 貸出のリスト
 */
public class Loans {
    List<Loan> list;

    public Loans(List<Loan> list) {
        this.list = list;
    }

    public int count() {
        return list.size();
    }

    public List<Loan> asList() {
        return Collections.unmodifiableList(list);
    }

    public int 視聴覚資料の数() {
        return (int)list.stream().filter(l -> l.item.所蔵品目().所蔵品目種別() == EntryType.視聴覚資料).count();
    }
}

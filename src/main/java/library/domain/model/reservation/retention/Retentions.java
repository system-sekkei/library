package library.domain.model.reservation.retention;

import library.domain.model.item.Item;

import java.util.List;

/**
 * 取置の一覧
 */
public class Retentions {
    List<Retained> list;

    public Retentions(List<Retained> list) {
        this.list = list;
    }

    public static Retentions empty() {
        return new Retentions(List.of());
    }

    public boolean notContains(Item item) {
        return list.stream().noneMatch(retained -> retained.isA(item));
    }

    public List<Retained> asList() {
        return list;
    }

    public String showCount() {
        if (list.size() == 0) return "取置中の蔵書はありません";
        return list.size() + "件を取り置いています";
    }
}

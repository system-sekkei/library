package library.domain.model.reservation.retention;

import library.domain.model.item.Item;

import java.util.List;

/**
 * 準備完了の一覧
 */
public class RetainedList {
    List<Retained> list;

    public RetainedList(List<Retained> list) {
        this.list = list;
    }

    public static RetainedList empty() {
        return new RetainedList(List.of());
    }

    public List<Retained> asList() {
        return list;
    }

    public String showCount() {
        if (list.size() == 0) return "取置中の蔵書はありません";
        return list.size() + "件を取り置いています";
    }

    @Override
    public String toString() {
        return "Retentions{" +
                "list=" + list +
                '}';
    }
}

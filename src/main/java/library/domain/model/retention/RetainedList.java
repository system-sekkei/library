package library.domain.model.retention;

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
        if (list.size() == 0) return "取置中の所蔵品はありません";
        return list.size() + "件を取り置いています";
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public RetainedList 期限切れの取置一覧() {

    }

    @Override
    public String toString() {
        return "Retentions{" +
                "list=" + list +
                '}';
    }
}

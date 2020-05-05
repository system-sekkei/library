package library.domain.model.reservation.retention;

import library.domain.model.book.item.Item;

import java.util.List;

/**
 * 取置棚
 */
public class RetentionShelf {
    List<RetainedHolding> list;

    public RetentionShelf(List<RetainedHolding> list) {
        this.list = list;
    }

    public static RetentionShelf empty() {
        return new RetentionShelf(List.of());
    }

    public boolean notContains(Item item) {
        return list.stream().noneMatch(retainedHolding -> retainedHolding.isA(item));
    }
}

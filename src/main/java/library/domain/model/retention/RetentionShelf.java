package library.domain.model.retention;

import library.domain.model.holding.Holding;

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

    public boolean notContains(Holding holding) {
        return list.stream().noneMatch(retainedHolding -> retainedHolding.isA(holding));
    }
}

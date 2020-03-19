package library.domain.model.retention;

import library.domain.model.holding.Holding;

import java.util.List;

/**
 * 取置のリスト
 */
public class Retentions {
    List<RetainedHolding> list;

    public Retentions(List<RetainedHolding> list) {
        this.list = list;
    }

    public boolean notContains(Holding holding) {
        return list.stream().noneMatch(retainedHolding -> retainedHolding.isA(holding));
    }
}

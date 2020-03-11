package library.domain.model.holding;

import java.util.List;

/**
 * 蔵書リスト
 */
public class Holdings {
    List<Holding> list;

    public Holdings(List<Holding> list) {
        this.list = list;
    }

    public List<Holding> list() {
        return list;
    }
}

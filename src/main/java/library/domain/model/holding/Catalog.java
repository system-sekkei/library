package library.domain.model.holding;

import java.util.List;

/**
 * 目録
 */
public class Catalog {
    List<Holding> list;

    public Catalog(List<Holding> list) {
        this.list = list;
    }

    public List<Holding> list() {
        return list;
    }
}

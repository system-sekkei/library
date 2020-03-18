package library.domain.model.holding;

import java.util.List;

/**
 * 在庫中の蔵書リスト
 */
public class HoldingsInStock {
    List<HoldingInStock> list;

    public HoldingsInStock(List<HoldingInStock> list) {
        this.list = list;
    }

    public List<HoldingInStock> list() {
        return list;
    }
}

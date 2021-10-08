package library.domain.model.material.instock;

/**
 * 在庫数
 */
public class StockQuantity {
    int value;

    public StockQuantity(int value) {
        this.value = value;
    }

    @Deprecated
    StockQuantity() {
    }

    public StockQuantity 引く(int 自身より前の予約人数) {
        return new StockQuantity(value - 自身より前の予約人数);
    }

    public int value() {
        return value;
    }
}

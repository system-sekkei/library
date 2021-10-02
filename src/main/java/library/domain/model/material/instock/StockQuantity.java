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
}

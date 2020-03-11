package library.domain.model.holding;

/**
 * 在庫中の蔵書
 */
public class HoldingInStock {
    Holding holding;

    public HoldingInStock(Holding holding) {
        this.holding = holding;
    }

    public Holding holding() {
        return holding;
    }
}

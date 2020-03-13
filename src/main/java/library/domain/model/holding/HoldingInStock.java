package library.domain.model.holding;

/**
 * 在庫中の蔵書
 */
public class HoldingInStock {
    Holding holding;

    public HoldingInStock(Holding holding) {
        this.holding = holding;
    }

    public HoldingStatus holdingStatus() {
        return HoldingStatus.在庫中;
    }

    public Holding holding() {
        return holding;
    }
}

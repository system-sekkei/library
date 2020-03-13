package library.domain.model.holding;

/**
 * 貸出中の蔵書
 */
public class HoldingOnLoan {
    Holding holding;

    public HoldingOnLoan(Holding holding) {
        this.holding = holding;
    }

    public HoldingStatus holdingStatus() {
        return HoldingStatus.貸出中;
    }

    public Holding holding() {
        return holding;
    }
}

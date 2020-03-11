package library.domain.model.holding;

/**
 * 貸出中の蔵書
 */
public class HoldingOnLoan {
    Holding holding;

    public HoldingOnLoan(Holding holding) {
        this.holding = holding;
    }

    public Holding holding() {
        return holding;
    }
}

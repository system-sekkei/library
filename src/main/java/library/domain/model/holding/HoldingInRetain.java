package library.domain.model.holding;

/**
 * 取置中の蔵書
 */
public class HoldingInRetain {
    Holding holding;

    public HoldingInRetain(Holding holding) {
        this.holding = holding;
    }

    public HoldingStatus holdingStatus() {
        return HoldingStatus.取置中;
    }

    public Holding holding() {
        return holding;
    }
}

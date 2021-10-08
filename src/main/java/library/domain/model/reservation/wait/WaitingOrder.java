package library.domain.model.reservation.wait;

/**
 * 待ち順番
 */
public class WaitingOrder {
    int value;

    public WaitingOrder(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Deprecated
    WaitingOrder() {
    }
}

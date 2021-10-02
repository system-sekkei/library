package library.domain.model.reservation.wait;

/**
 * 待ち順番
 */
public class WaitingOrder {
    int value;

    public WaitingOrder(int value) {
        this.value = value;
    }

    @Deprecated
    WaitingOrder() {
    }
}

package library.domain.model.bookonloan.loaning;

/**
 * 貸し出せない理由
 */
public class RejectReason {
    String value;

    public RejectReason(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

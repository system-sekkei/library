package library.domain.model.loan.rule;

/**
 * 貸し出せない理由
 */
public enum RejectReason {
    貸出冊数超過("これ以上本を貸し出すことができません。"),
    蔵書が貸出中("現在貸出中の蔵書です。");

    String message;

    RejectReason(String message) {
        this.message = message;
    }
}

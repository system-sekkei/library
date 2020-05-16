package library.domain.model.loan.rule;

/**
 * 貸出可否
 */
public enum Restriction {
    貸出不可("これ以上本を貸し出すことができません。"),
    貸出可能("");

    String message;

    Restriction(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}

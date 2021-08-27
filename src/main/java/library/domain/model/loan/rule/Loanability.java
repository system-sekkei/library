package library.domain.model.loan.rule;

/**
 * 貸出可否
 */
public enum Loanability {
    冊数制限により貸出不可("これ以上本を貸し出すことができません。"),
    貸出中により貸出不可("貸出中であるため貸し出すことができません。"),
    視聴覚資料貸出不可("これ以上視聴覚資料を貸し出すことができません。"),
    貸出可能("");

    String message;

    Loanability(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}

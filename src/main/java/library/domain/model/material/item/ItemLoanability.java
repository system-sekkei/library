package library.domain.model.material.item;

/**
 * 所蔵品の貸出可否
 */
public enum ItemLoanability {
    貸出可能(""),
    貸出中により貸出不可能("貸出中であるため貸し出すことができません。"),
    予約中により貸出不可能("予約中であるため貸し出すことができません。"),
    その他の理由で貸出不可能("貸し出すことができません。");

    String message;

    ItemLoanability(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}

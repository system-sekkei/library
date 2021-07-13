package library.domain.model.reservation.loanability;

/**
 * 貸出可否
 */
public enum Loanability {
    貸出できる("〇"),
    貸出できない("×");

    String label;

    Loanability(String label) {
        this.label = label;
    }
    public static Loanability loanability(int loanable) {
        if (loanable > 0) return 貸出できる;
        return 貸出できない;
    }

    public String show() {
        return label;
    }
}

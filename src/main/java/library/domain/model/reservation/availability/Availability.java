package library.domain.model.reservation.availability;

/**
 * 貸出可否
 */
public enum Availability {
    貸出できる("〇"),
    貸出できない("×");

    String label;

    Availability(String label) {
        this.label = label;
    }
    public static Availability availability(int loanable) {
        if (loanable > 0) return 貸出できる;
        return 貸出できない;
    }

    public String show() {
        return label;
    }
}

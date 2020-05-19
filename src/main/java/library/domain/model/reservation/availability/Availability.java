package library.domain.model.reservation.availability;

/**
 * 予約可否
 */
public enum Availability {
    予約できる("〇"),
    予約できない("×");

    String label;

    Availability(String label) {
        this.label = label;
    }
    public static Availability availability(int loanable) {
        if (loanable > 0) return 予約できる;
        return 予約できない;
    }

    public String show() {
        return label;
    }
}

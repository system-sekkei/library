package library.domain.model.reservation;


/**
 * 予約の件数
 */
public class NumberOfReservation {
    int value;

    public NumberOfReservation(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == 0) return "取置が必要な予約はありません";
        return value + "件";
    }

    public boolean より少ない(NumberOfReservation 冊数) {
        return value > 冊数.value;
    }

    public boolean 以下(NumberOfReservation 冊数) {
        return value >= 冊数.value;
    }

    public NumberOfReservation 足す(int value) {
        return new NumberOfReservation(this.value + value);
    }

    public boolean より多い(NumberOfReservation 冊数) {
        return value < 冊数.value;
    }
}

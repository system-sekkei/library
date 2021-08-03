package library.domain.type.date;

/**
 * 月数
 */
public class Months {
    int value;

    public Months(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public boolean 未満(int other) {
        return value < other;
    }

    public boolean 以上(int other) {
        return value >= other;
    }
}

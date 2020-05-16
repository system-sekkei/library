package library.domain.type.date;

/**
 * 日数
 */
public class Days {
    int value;

    public Days(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public boolean lessThan(int other) {
        return value < other;
    }
}

package library.domain.model.loan.history;

/**
 * 履歴件数
 */
public class HistoryCount {
    int value;

    public HistoryCount(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public int minus(HistoryCount other) {
        return value - other.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

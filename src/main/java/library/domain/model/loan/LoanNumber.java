package library.domain.model.loan;

/**
 * 貸出番号
 */
public class LoanNumber {
    int value;

    public LoanNumber(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Deprecated
    LoanNumber() {
    }
}

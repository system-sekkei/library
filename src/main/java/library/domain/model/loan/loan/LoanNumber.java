package library.domain.model.loan.loan;

/**
 * 貸出番号
 */
public class LoanNumber {
    int value;

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

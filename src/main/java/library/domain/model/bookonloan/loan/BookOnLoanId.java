package library.domain.model.bookonloan.loan;

/**
 * 貸出図書ID
 */
public class BookOnLoanId {
    Integer value;

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

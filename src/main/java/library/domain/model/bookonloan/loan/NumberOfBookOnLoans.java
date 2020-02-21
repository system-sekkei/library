package library.domain.model.bookonloan.loan;

/**
 * 貸出図書の冊数
 */
public class NumberOfBookOnLoans {
    int value;

    public NumberOfBookOnLoans(int value) {

        this.value = value;
    }

    public int value() {
        return value;
    }
}

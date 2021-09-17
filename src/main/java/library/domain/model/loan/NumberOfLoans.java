package library.domain.model.loan;

/**
 * 貸出冊数
 */
public class NumberOfLoans {
    int value;

    public NumberOfLoans(int value) {
        this.value = value;
    }

    public NumberOfLoans(long count) {
        this((int) count);
    }

    public static NumberOfLoans of(int value) {
        return new NumberOfLoans(value);
    }

    public int value() {
        return value;
    }

    public boolean より少ない(NumberOfLoans 冊数) {
        return value > 冊数.value();
    }

    public boolean 以下(NumberOfLoans 冊数) {
        return value() >= 冊数.value();
    }

    public NumberOfLoans 足す(int value) {
        return new NumberOfLoans(value() + value);
    }

    public boolean より多い(NumberOfLoans 冊数) {
        return value() < 冊数.value();
    }
}

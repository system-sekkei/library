package library.domain.model.loan;

/**
 * 貸出点数
 */
public class NumberOfLoans {
    int value;

    public static final NumberOfLoans _5点 = of(5);
    public static final NumberOfLoans _15点 = of(15);
    public static final NumberOfLoans _20点 = of(20);

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

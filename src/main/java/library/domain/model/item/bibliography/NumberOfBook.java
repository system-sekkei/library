package library.domain.model.item.bibliography;

/**
 * 本の点数
 * (本の種類の数)
 */
public class NumberOfBook {
    int value;

    public NumberOfBook(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

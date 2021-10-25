package library.domain.model.retention;

/**
 * 取置番号
 */
public class RetentionNumber {
    int value;

    @Deprecated
    RetentionNumber() {
    }

    public RetentionNumber(String textValue) {
        this.value = Integer.parseInt(textValue);
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public boolean sameValue(RetentionNumber other) {
        return this.value == other.value;
    }
}

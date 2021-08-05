package library.domain.model.material.bibliography;

import javax.validation.constraints.NotNull;

/**
 * 資料番号
 */
public class BookNumber {
    @NotNull(message = "本IDを入力してください。")
    int value;

    @Deprecated
    BookNumber() {
    }

    public BookNumber(int value) {
        this.value = value;
    }
    public BookNumber(String value) {
        this.value = Integer.parseInt(value);
    }

    public int value() {
        return value;
    }

    public boolean sameValue(BookNumber other) {
        return this.value == other.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

package library.domain.model.item.bibliography;

import javax.validation.constraints.NotNull;

/**
 * 書籍番号
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

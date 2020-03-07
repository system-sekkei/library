package library.domain.model.book;

import javax.validation.constraints.NotNull;

/**
 * 本ID
 */
public class BookId {
    @NotNull(message = "本IDを入力してください。")
    int value;

    @Deprecated
    BookId() {
    }

    public BookId(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public boolean sameValue(BookId other) {
        return this.value == other.value;
    }
}

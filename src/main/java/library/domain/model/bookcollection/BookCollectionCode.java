package library.domain.model.bookcollection;

import javax.validation.constraints.NotBlank;

/**
 * 蔵書コード
 */
public class BookCollectionCode {
    @NotBlank(message = "蔵書コードを入力してください。")
    String value;

    public BookCollectionCode(String value) {
        this.value = value;
    }

    @Deprecated
    BookCollectionCode() {
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean sameValue(BookCollectionCode other) {
        return value.equals(other.value);
    }
}

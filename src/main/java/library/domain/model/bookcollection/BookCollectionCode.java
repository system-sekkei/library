package library.domain.model.bookcollection;

import javax.validation.constraints.NotBlank;

/**
 * 蔵書コード
 */
public class BookCollectionCode {
    @NotBlank(message = "蔵書コードを入力してください。")
    String value;

    public BookCollectionCode() {
    }

    public BookCollectionCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

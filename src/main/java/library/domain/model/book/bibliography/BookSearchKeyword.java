package library.domain.model.book.bibliography;

/**
 * 本の検索キーワード
 */
public class BookSearchKeyword {
    String value;

    public BookSearchKeyword(String value) {
        this.value = value;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isBlank() {
        return value.isBlank();
    }

    @Override
    public String toString() {
        return value;
    }
}

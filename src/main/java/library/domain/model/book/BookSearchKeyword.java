package library.domain.model.book;

/**
 * 本の検索キーワード
 */
public class BookSearchKeyword {
    String value;

    public BookSearchKeyword(String value) {
        this.value = value;
    }

    public boolean isBlank() {
        return value.isBlank();
    }
}

package library.domain.model.book.bibliography;

/**
 * 検索キーワード
 */
public class Keyword {
    String value;

    public Keyword(String value) {
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

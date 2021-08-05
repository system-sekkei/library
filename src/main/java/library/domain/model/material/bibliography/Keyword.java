package library.domain.model.material.bibliography;

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

    @Deprecated(since = "thymeleaf inspection")
    public void value() {
    }

    @Override
    public String toString() {
        return value;
    }
}

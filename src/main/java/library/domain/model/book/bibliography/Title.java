package library.domain.model.book.bibliography;

/**
 * 書籍の題名
 */
public class Title {
    String value;

    public Title(String value) {
        this.value = value;
    }

    @Deprecated
    Title() {
    }

    @Override
    public String toString() {
        return value;
    }
}

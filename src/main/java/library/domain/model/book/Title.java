package library.domain.model.book;

/**
 * 本のタイトル
 */
public class Title {
    String value;

    public Title(String value) {
        this.value = value;
    }

    @Deprecated
    Title() {
    }
}

package library.domain.model.item.bibliography;

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

    @Override
    public String toString() {
        return value;
    }
}

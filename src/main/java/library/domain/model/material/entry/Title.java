package library.domain.model.material.entry;

/**
 * 図書の題名
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

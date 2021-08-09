package library.domain.model.material.entry;

/**
 * 著者
 */
public class Author {
    String value;

    public Author(String value) {
        this.value = value;
    }

    @Deprecated
    Author() {
    }

    @Override
    public String toString() {
        return value;
    }
}

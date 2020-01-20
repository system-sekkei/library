package library.domain.model.member;

/**
 * 名前
 */
public class Name {
    String value;

    public Name(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

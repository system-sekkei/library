package library.domain.model.bookcollection;

/**
 * 蔵書コード
 */
public class BookCollectionCode {
    String value;

    public BookCollectionCode() {
    }

    public BookCollectionCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

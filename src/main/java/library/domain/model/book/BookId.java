package library.domain.model.book;

/**
 * 本ID
 */
public class BookId {
    int value;

    @Deprecated
    BookId() {
    }

    public BookId(int value) {
        this.value = value;
    }
}

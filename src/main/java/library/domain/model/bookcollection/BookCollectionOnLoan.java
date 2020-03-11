package library.domain.model.bookcollection;

/**
 * 貸出中の蔵書
 */
public class BookCollectionOnLoan {
    BookCollection bookCollection;

    public BookCollectionOnLoan(BookCollection bookCollection) {
        this.bookCollection = bookCollection;
    }

    public BookCollection bookCollection() {
        return bookCollection;
    }
}

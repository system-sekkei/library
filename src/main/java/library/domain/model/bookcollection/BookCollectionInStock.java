package library.domain.model.bookcollection;

/**
 * 在庫中の蔵書
 */
public class BookCollectionInStock {
    BookCollection bookCollection;

    public BookCollectionInStock(BookCollection bookCollection) {
        this.bookCollection = bookCollection;
    }

    public BookCollection bookCollection() {
        return bookCollection;
    }
}

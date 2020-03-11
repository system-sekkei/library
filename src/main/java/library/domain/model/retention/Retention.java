package library.domain.model.retention;


import library.domain.model.book.Book;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionInStock;
import library.domain.model.bookcollection.BookCollectionsInStock;

/**
 * 取置
 */
public class Retention {
    BookCollectionsInStock bookCollectionsInStock;
    Retentions retentions;

    Retentionability retentionability(Book book) {
        for (BookCollectionInStock bookCollectionInStock : bookCollectionsInStock.list()) {
            if (bookCollectionInStock.bookCollection().sameBook(book) && retentionability(bookCollectionInStock.bookCollection())) {
                return Retentionability.取置可能;
            }
        }
        return Retentionability.取置不可能;
    }

    private boolean retentionability(BookCollection bookCollection) {
        return retentions.notContains(bookCollection);
    }

}

package library.domain.model.retention;


import library.domain.model.book.Book;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollections;

/**
 * 取置
 */
public class Retention {
    BookCollections bookCollections;
    Retentions retentions;

    Retentionability retentionability(Book book) {
        for (BookCollection bookCollection : bookCollections.list()) {
            if (bookCollection.sameBook(book) && retentionability(bookCollection)) {
                return Retentionability.取置可能;
            }
        }
        return Retentionability.取置不可能;
    }

    private boolean retentionability(BookCollection bookCollection) {
        return bookCollection.bookCollectionStatus.retentionable() && retentions.notContains(bookCollection);
    }

}

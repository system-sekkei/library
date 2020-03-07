package library.domain.model.bookcollection;

import library.domain.model.book.Book;

/**
 * 同一本の蔵書リスト
 */
public class SameBooksBookCollections {
    Book book;
    BookCollections bookCollections;

    public BookCollectionStatus bookCollectionStatus() {
        if (bookCollections.list.stream().anyMatch(bookCollection ->
            bookCollection.bookCollectionStatus == BookCollectionStatus.在庫中)) {
            return BookCollectionStatus.在庫中;
        }
        return BookCollectionStatus.貸出中;
    }

    public Book book() {
        return book;
    }
}

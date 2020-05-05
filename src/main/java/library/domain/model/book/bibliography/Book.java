package library.domain.model.book.bibliography;

/**
 * 本
 */
public class Book {
    BookId bookId;
    Title title;
    Author author;
    BookType bookType;

    @Deprecated
    Book() {
    }

    public Book(BookId bookId, Title title, Author author, BookType bookType) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.bookType = bookType;
    }

    public BookId bookId() {
        return bookId;
    }

    public Title title() {
        return title;
    }

    public Author author() {
        return author;
    }

    public BookType bookType() {
        return bookType;
    }

    public boolean sameBook(Book other) {
        return bookId().sameValue(other.bookId);
    }
}

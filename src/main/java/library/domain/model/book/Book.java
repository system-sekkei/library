package library.domain.model.book;

/**
 * 本
 */
public class Book {
    Title title;
    Author author;
    BookType bookType;

    @Deprecated
    Book() {
    }

    public Book(Title title, Author author, BookType bookType) {
        this.title = title;
        this.author = author;
        this.bookType = bookType;
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
}

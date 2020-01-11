package library.domain.model.book;

/**
 * 本
 */
public class Book {
    Title title;
    Author author;

    public Book(Title title, Author author) {
        this.title = title;
        this.author = author;
    }
}

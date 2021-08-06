package library.domain.model.material.bibliography;

/**
 * 図書
 */
public class Book {
    Title title;
    Author author;

    public Book(Title title, Author author) {
        this.title = title;
        this.author = author;
    }

    @Deprecated
    Book() {
    }

    public Title title() {
        return title;
    }

    public Author author() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title=" + title +
                ", author=" + author +
                '}';
    }
}

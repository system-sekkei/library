package library.domain.model.material.entry;

/**
 * 所蔵品目
 */
public class Entry {
    Title title;
    Author author;

    public Entry(Title title, Author author) {
        this.title = title;
        this.author = author;
    }

    @Deprecated
    Entry() {
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

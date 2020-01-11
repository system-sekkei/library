package library.domain.model.collection;

/**
 * 蔵書
 */
public class Collection {
    Title title;
    Author author;

    public Collection(Title title, Author author) {
        this.title = title;
        this.author = author;
    }
}

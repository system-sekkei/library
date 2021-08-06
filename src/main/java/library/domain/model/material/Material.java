package library.domain.model.material;

import library.domain.model.material.bibliography.Book;
import library.domain.model.material.bibliography.Title;

/**
 * 資料
 */
public class Material {
    MaterialNumber materialNumber;
    Book book;

    @Deprecated
    Material() {
    }

    public Material(MaterialNumber materialNumber, Book book) {
        this.materialNumber = materialNumber;
        this.book = book;
    }

    public MaterialNumber materialNumber() {
        return materialNumber;
    }

    public boolean isSame(Material other) {
        return materialNumber.sameValue(other.materialNumber);
    }
    public Title title() {
        return book.title();
    }

    public String show() {
        return String.format("%s (%s)", book.title(), book.author());
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialNumber=" + materialNumber +
                ", book=" + book +
                '}';
    }
}

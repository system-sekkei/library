package library.domain.model.material;

import library.domain.model.material.bibliography.Author;
import library.domain.model.material.bibliography.Title;

/**
 * 資料
 */
public class Material {
    MaterialNumber materialNumber;
    Title title;
    Author author;

    @Deprecated
    Material() {
    }

    public Material(MaterialNumber materialNumber, Title title, Author author) {
        this.materialNumber = materialNumber;
        this.title = title;
        this.author = author;
    }

    public MaterialNumber materialNumber() {
        return materialNumber;
    }

    public boolean isSame(Material other) {
        return materialNumber.sameValue(other.materialNumber);
    }
    public Title title() {
        return title;
    }

    public String show() {
        return String.format("%s (%s)", title, author);
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialNumber=" + materialNumber +
                ", title=" + title +
                ", author=" + author +
                '}';
    }
}

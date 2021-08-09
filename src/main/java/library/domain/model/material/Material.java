package library.domain.model.material;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.Title;

/**
 * 資料
 */
public class Material {
    MaterialNumber materialNumber;
    Entry entry;

    @Deprecated
    Material() {
    }

    public Material(MaterialNumber materialNumber, Entry entry) {
        this.materialNumber = materialNumber;
        this.entry = entry;
    }

    public MaterialNumber materialNumber() {
        return materialNumber;
    }

    public boolean isSame(Material other) {
        return materialNumber.sameValue(other.materialNumber);
    }
    public Title title() {
        return entry.title();
    }

    public String show() {
        return String.format("%s (%s)", entry.title(), entry.author());
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialNumber=" + materialNumber +
                ", book=" + entry +
                '}';
    }
}

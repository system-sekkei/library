package library.domain.model.material.entry;

/**
 * 資料
 */
public class Material {
    MaterialNumber materialNumber;
    Title title;
    WorkOf workOf;

    @Deprecated
    Material() {
    }

    public Material(MaterialNumber materialNumber, Title title, WorkOf workOf) {
        this.materialNumber = materialNumber;
        this.title = title;
        this.workOf = workOf;
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

    public WorkOf workOf() {
        return workOf;
    }

    public String show() {
        return String.format("%s (%s)", title(), workOf());
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialNumber=" + materialNumber +
                ", title=" + title +
                ", workOf=" + workOf +
                '}';
    }
}

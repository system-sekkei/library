package library.domain.model.material.entry;

/**
 * 所蔵品目
 */
public class Entry {
    Title title;
    WorkOf workOf;

    public Entry(Title title, WorkOf workOf) {
        this.title = title;
        this.workOf = workOf;
    }

    @Deprecated
    Entry() {
    }

    public Title title() {
        return title;
    }

    public WorkOf author() {
        return workOf;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title=" + title +
                ", author=" + workOf +
                '}';
    }
}

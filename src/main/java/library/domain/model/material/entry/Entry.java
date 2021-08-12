package library.domain.model.material.entry;

/**
 * 所蔵品目
 */
public class Entry {
    EntryNumber entryNumber;
    Title title;
    WorkOf workOf;
    EntryType entryType;

    @Deprecated
    Entry() {
    }

    public Entry(EntryNumber entryNumber, Title title, WorkOf workOf, EntryType entryType) {
        this.entryNumber = entryNumber;
        this.title = title;
        this.workOf = workOf;
        this.entryType = entryType;
    }

    public EntryNumber entryNumber() {
        return entryNumber;
    }

    public boolean isSame(Entry other) {
        return entryNumber.sameValue(other.entryNumber);
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
        return "Entry{" +
                "entryNumber=" + entryNumber +
                ", title=" + title +
                ", workOf=" + workOf +
                ", entryType=" + entryType +
                '}';
    }
}

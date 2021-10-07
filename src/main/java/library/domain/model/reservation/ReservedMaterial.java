package library.domain.model.reservation;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;

/**
 * 予約図書
 */
public class ReservedMaterial {
    Entry entry;

    public ReservedMaterial(Entry entry) {
        this.entry = entry;
    }

    @Deprecated
    ReservedMaterial() {
    }

    public Entry entry() {
        return entry;
    }

    public boolean isA(EntryNumber entryNumber) {
        return entry.entryNumber().sameValue(entryNumber);
    }

    @Override
    public String toString() {
        return "ReservedMaterial{" +
                "material=" + entry +
                '}';
    }
}

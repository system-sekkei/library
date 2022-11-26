package library.domain.model.material.entry;

import jakarta.validation.constraints.NotNull;

/**
 * 所蔵品目番号
 */
public class EntryNumber {
    @NotNull(message = "所蔵品目IDを入力してください。")
    int value;

    @Deprecated
    EntryNumber() {
    }

    public EntryNumber(int value) {
        this.value = value;
    }
    public EntryNumber(String value) {
        this.value = Integer.parseInt(value);
    }

    public int value() {
        return value;
    }

    public boolean sameValue(EntryNumber other) {
        return this.value == other.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

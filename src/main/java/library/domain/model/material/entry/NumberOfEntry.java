package library.domain.model.material.entry;

/**
 * 所蔵品目の点数
 * (所蔵品目の種類の数)
 */
public class NumberOfEntry {
    int value;

    public NumberOfEntry(int value) {
        this.value = value;
    }

    public static int MAX_TO_SHOW = 20;
    public String show() {
        String over = value > MAX_TO_SHOW ? "以上" : "";
        return value + "件" + over;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

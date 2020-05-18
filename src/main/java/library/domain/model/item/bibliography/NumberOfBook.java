package library.domain.model.item.bibliography;

/**
 * 本の点数
 * (本の種類の数)
 */
public class NumberOfBook {
    int value;

    NumberOfBook(int value) {
        this.value = value;
    }

    public static int MAX_TO_SHOW = 20;
    String show() {
        String over = value > MAX_TO_SHOW ? "以上" : "";
        return value + "件" + over;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

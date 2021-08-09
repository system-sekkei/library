package library.domain.model.material.entry;

import javax.validation.constraints.NotNull;

/**
 * 資料番号
 */
public class MaterialNumber {
    @NotNull(message = "本IDを入力してください。")
    int value;

    @Deprecated
    MaterialNumber() {
    }

    public MaterialNumber(int value) {
        this.value = value;
    }
    public MaterialNumber(String value) {
        this.value = Integer.parseInt(value);
    }

    public int value() {
        return value;
    }

    public boolean sameValue(MaterialNumber other) {
        return this.value == other.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

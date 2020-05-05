package library.domain.model.book.item;

import javax.validation.constraints.NotBlank;

/**
 * 蔵書番号
 */
public class ItemNumber {
    @NotBlank(message = "蔵書コードを入力してください。")
    String value;

    public ItemNumber(String value) {
        this.value = value;
    }

    @Deprecated
    ItemNumber() {
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean sameValue(ItemNumber other) {
        return value.equals(other.value);
    }
}

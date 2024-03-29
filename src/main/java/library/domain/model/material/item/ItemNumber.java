package library.domain.model.material.item;

import jakarta.validation.constraints.NotBlank;

/**
 * 所蔵品番号
 */
public class ItemNumber {
    @NotBlank(message = "所蔵品番号を入力してください。")
    String value;

    public ItemNumber(String value) {
        this.value = value;
    }

    @Deprecated
    ItemNumber() {
    }

    public boolean sameValue(ItemNumber other) {
        return value.equals(other.value);
    }

    public static ItemNumber empty() {
        return new ItemNumber("");
    }

    @Deprecated(since = "thymeleaf")
    public void value() {
    }

    @Override
    public String toString() {
        return value;
    }
}

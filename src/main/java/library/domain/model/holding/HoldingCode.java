package library.domain.model.holding;

import javax.validation.constraints.NotBlank;

/**
 * 蔵書コード
 */
public class HoldingCode {
    @NotBlank(message = "蔵書コードを入力してください。")
    String value;

    public HoldingCode(String value) {
        this.value = value;
    }

    @Deprecated
    HoldingCode() {
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean sameValue(HoldingCode other) {
        return value.equals(other.value);
    }
}

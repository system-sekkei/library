package library.domain.model.member;

import javax.validation.constraints.NotNull;

/**
 * 会員番号
 */
public class MemberNumber {
    @NotNull(message = "会員番号を入力してください。")
    int value;

    public MemberNumber(int value) {
        this.value = value;
    }

    @Deprecated
    MemberNumber() {
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        if (value == 0) return "";
        return Integer.toString(value);
    }
}

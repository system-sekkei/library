package library.domain.model.member;

import jakarta.validation.constraints.NotNull;

/**
 * 会員番号
 */
public class MemberNumber {
    @NotNull(message = "会員番号を入力してください。")
    Integer value;

    public MemberNumber(Integer value) {
        this.value = value;
    }

    @Deprecated
    MemberNumber() {
    }

    public Integer value() {
        return value;
    }

    public static MemberNumber empty() {
        return new MemberNumber();
    }
    @Override
    public String toString() {
        if (value == null || value == 0) return "";
        return Integer.toString(value);
    }

    public boolean sameValue(MemberNumber other) {
        return value.equals(other.value);
    }
}

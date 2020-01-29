package library.domain.model.member;

import javax.validation.constraints.NotNull;

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

    public static MemberNumber from(String value) {
        return new MemberNumber(Integer.parseInt(value));
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

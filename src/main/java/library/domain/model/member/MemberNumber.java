package library.domain.model.member;

/**
 * 会員番号
 */
public class MemberNumber {
    Integer value;

    public MemberNumber(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

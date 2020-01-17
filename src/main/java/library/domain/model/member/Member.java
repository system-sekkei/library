package library.domain.model.member;

/**
 * 会員
 */
public class Member {
    Name name;
    MemberType memberType;

    public Member(Name name, MemberType memberType) {
        this.name = name;
        this.memberType = memberType;
    }

    public MemberType memberType() {
        return memberType;
    }
}

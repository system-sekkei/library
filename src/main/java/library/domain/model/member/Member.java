package library.domain.model.member;

/**
 * 会員
 */
public class Member {
    MemberNumber memberNumber;
    Name name;
    MemberType memberType;

    public Member(MemberNumber memberNumber, Name name, MemberType memberType) {
        this.memberNumber = memberNumber;
        this.name = name;
        this.memberType = memberType;
    }

    @Deprecated
    Member() {
    }

    public MemberNumber number() {
        return memberNumber;
    }

    public Name name() {
        return name;
    }

    public MemberType type() {
        return memberType;
    }
}

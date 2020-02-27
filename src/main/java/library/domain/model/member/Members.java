package library.domain.model.member;

import java.util.List;

/**
 * 会員リスト
 */
public class Members {
    List<Member> list;

    public Members(List<Member> list) {
        this.list = list;
    }
}

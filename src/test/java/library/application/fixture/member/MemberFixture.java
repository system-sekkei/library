package library.application.fixture.member;

import library.domain.model.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemberFixture {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void 会員を登録する(Member member) {

        String sql = """
                INSERT INTO 会員.会員(会員番号, 氏名, 会員種別)
                VALUES (?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                member.number().value(),
                member.name().toString(),
                member.type().name());

    }




}

package library.application.fixture.entry;

import library.domain.model.material.entry.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EntryFixture {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void 所蔵品目を登録する(Entry entry) {
        String sql = """
                INSERT INTO 資料_所蔵品目.所蔵品目(所蔵品目番号, タイトル, 著者, 所蔵品目種別)
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                entry.entryNumber().value(),
                entry.title().toString(),
                entry.workOf().toString(),
                entry.所蔵品目種別().name());
    }
}

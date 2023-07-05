package library.application.fixture.item;

import library.domain.model.material.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ItemFixture {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void 所蔵品を登録する(Item item) {
        String sql = """
            INSERT INTO 資料_所蔵品.所蔵品(所蔵品番号, 所蔵品目番号)
            VALUES(?, ?)
            """;

        jdbcTemplate.update(sql,
                item.所蔵品番号().toString(),
                item.所蔵品目().entryNumber().value());
    }

    public void 貸出可能な状態を登録する(Item item) {
        String sql = """
            INSERT INTO 資料_所蔵品._貸出可能(所蔵品番号)
            VALUES (?)
            """;

        jdbcTemplate.update(sql,
                item.所蔵品番号().toString());
    }
}

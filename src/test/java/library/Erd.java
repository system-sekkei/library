package library;

import jig.erd.JigErd;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@Disabled("mssqlはサポート対象外")
@SpringBootTest
public class Erd {

    @Disabled("mssqlはサポート対象外")
    @Test
    void run(@Autowired DataSource dataSource) {
        JigErd.run(dataSource);
    }
}

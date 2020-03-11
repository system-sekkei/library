package library.infrastructure.datasource.holding;

import library.LibraryDBTest;
import library.domain.model.book.BookId;
import library.domain.model.holding.Holding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class HoldingMapperTest {

    @Autowired
    HoldingMapper holdingMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 本IDで在庫有りの蔵書一覧を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("holdingCode.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        List<Holding> holdings = holdingMapper.selectInStockHoldingsByBookIds(List.of(new BookId(2)));

        assertEquals(7, holdings.size());
    }
}
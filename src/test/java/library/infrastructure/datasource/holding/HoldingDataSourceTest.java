package library.infrastructure.datasource.holding;

import library.LibraryDBTest;
import library.domain.model.holding.HoldingCode;
import library.domain.model.holding.HoldingOnLoan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class HoldingDataSourceTest {

    @Autowired
    HoldingDataSource holdingDataSource;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 貸出履歴が複数ある蔵書を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("holdingCode.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        mockMvc.perform(
                post("/returnbook/register")
                        .param("holdingCode.value", "2-A")
                        .param("returnDate.value", "2020-02-14"));

        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("holdingCode.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        HoldingOnLoan holdingOnLoan = holdingDataSource.findHoldingOnLoan(new HoldingCode("2-A"));

        assertEquals("2-A", holdingOnLoan.holding().holdingCode().toString());
    }
}
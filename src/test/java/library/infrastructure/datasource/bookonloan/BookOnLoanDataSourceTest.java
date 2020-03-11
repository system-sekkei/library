package library.infrastructure.datasource.bookonloan;

import library.LibraryDBTest;
import library.application.coordinator.returnbook.ReturnBookCoordinator;
import library.application.service.holding.HoldingQueryService;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.holding.HoldingCode;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class BookOnLoanDataSourceTest {

    @Autowired
    BookOnLoanDataSource bookOnLoanDataSource;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ReturnBookCoordinator returnBookCoordinator;

    @Autowired
    HoldingQueryService holdingQueryService;

    @Test
    void 蔵書コードで貸出図書を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("holdingCode.value", "2-C")
                        .param("loanDate.value", "2020-02-14"));

        HoldingCode holdingCode = new HoldingCode("2-C");
        BookOnLoan bookOnLoan = bookOnLoanDataSource.findBookOnLoanByHoldingCode(holdingCode);

        assertEquals("2020-02-14", bookOnLoan.loanDate().toString());
    }

    @Test
    void 返却した蔵書は取得できない() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("holdingCode.value", "2-D")
                        .param("loanDate.value", "2020-02-14"));

        HoldingCode holdingCode = new HoldingCode("2-D");
        returnBookCoordinator.returnBook(holdingCode, new ReturnDate(Date.from("2019-01-01")));

        assertThrows(IllegalArgumentException.class, () -> {
            bookOnLoanDataSource.findBookOnLoanByHoldingCode(holdingCode);
        });
    }
}
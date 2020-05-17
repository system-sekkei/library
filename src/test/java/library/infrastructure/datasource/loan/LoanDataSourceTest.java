package library.infrastructure.datasource.loan;

import library.LibraryDBTest;
import library.application.service.item.ItemQueryService;
import library.application.service.returns.ReturnBookRecordService;
import library.domain.model.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.returned.ReturnDate;
import library.domain.model.loan.returned.Returned;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class LoanDataSourceTest {

    @Autowired
    LoanDataSource loanDataSource;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ReturnBookRecordService returnBookRecordService;

    @Autowired
    ItemQueryService itemQueryService;

    @Test
    void 蔵書コードで貸出図書を取得できる() throws Exception {
        mockMvc.perform(
                post("/loan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-C")
                        .param("loanDate.value", "2020-02-14"));

        ItemNumber itemNumber = new ItemNumber("2-C");
        Loan loan = loanDataSource.findLoanByItemNumber(itemNumber);

        assertEquals("2020-02-14", loan.loanDate().toString());
    }

    @Test
    void 返却した蔵書は取得できない() throws Exception {
        mockMvc.perform(
                post("/loan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-D")
                        .param("loanDate.value", "2020-02-14"));

        ItemNumber itemNumber = new ItemNumber("2-D");
        ReturnDate returnDate = ReturnDate.parse("2019-01-01");
        returnBookRecordService.registerReturnBook(new Returned(itemNumber, returnDate));


        assertThrows(IllegalArgumentException.class, () -> {
            loanDataSource.findLoanByItemNumber(itemNumber);
        });
    }
}
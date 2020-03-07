package library.infrastructure.datasource.bookonloan;

import library.LibraryDBTest;
import library.application.coordinator.returnbook.ReturnBookCoordinator;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.loan.BookOnLoanId;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.type.date.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class BookOnLoanMapperTest {

    @Autowired
    BookOnLoanMapper bookOnLoanMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ReturnBookCoordinator returnBookCoordinator;

    @Test
    void 蔵書コードをもとに貸出を取得できる() {
        List<BookCollectionCode> bookCollectionCodes = List.of(new BookCollectionCode("1-A"));
        List<BookOnLoanData> bookOnLoanDatas = bookOnLoanMapper.selectByBookCollectionCodes(bookCollectionCodes);

        assertEquals(1, bookOnLoanDatas.size());
    }

    @Test
    void 蔵書コードをもとに返却を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("bookCollectionCode.value", "2-C")
                        .param("loanDate.value", "2020-02-14"));
        BookCollectionCode bookCollectionCode = new BookCollectionCode("2-C");
        returnBookCoordinator.returnBook(bookCollectionCode, new ReturnDate(Date.from("2019-01-01")));

        List<BookOnLoanId> bookOnLoanIds = bookOnLoanMapper.selectReturnedBookLoanIdByBookCollectionCodes(List.of(bookCollectionCode));

        assertEquals(2, bookOnLoanIds.get(0).value());
    }
}
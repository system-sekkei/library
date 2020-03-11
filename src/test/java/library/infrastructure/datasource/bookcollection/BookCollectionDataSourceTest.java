package library.infrastructure.datasource.bookcollection;

import library.LibraryDBTest;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookcollection.BookCollectionOnLoan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class BookCollectionDataSourceTest {

    @Autowired
    BookCollectionDataSource bookCollectionDataSource;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 貸出履歴が複数ある蔵書を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("bookCollectionCode.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        mockMvc.perform(
                post("/returnbook/register")
                        .param("bookCollectionCode.value", "2-A")
                        .param("returnDate.value", "2020-02-14"));

        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("bookCollectionCode.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        BookCollectionOnLoan bookCollection = bookCollectionDataSource.findBookCollectionOnLoan(new BookCollectionCode("2-A"));

        assertEquals("2-A", bookCollection.bookCollection().bookCollectionCode().toString());
    }
}
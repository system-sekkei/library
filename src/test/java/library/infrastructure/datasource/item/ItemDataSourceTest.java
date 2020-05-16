package library.infrastructure.datasource.item;

import library.LibraryDBTest;
import library.domain.model.item.Item;
import library.domain.model.item.ItemNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@LibraryDBTest
@AutoConfigureMockMvc
class ItemDataSourceTest {

    @Autowired
    ItemDataSource itemDataSource;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 貸出履歴が複数ある貸出中の蔵書を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        mockMvc.perform(
                post("/returnbook/register")
                        .param("itemNumber.value", "2-A")
                        .param("returnDate.value", "2020-02-14"));

        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        Item itemOnLoan = itemDataSource.findItemOnLoan(new ItemNumber("2-A"));

        assertEquals("2-A", itemOnLoan.itemNumber().toString());
    }

    @Test
    void 貸出履歴が複数ある在庫中の蔵書を取得できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("itemNumber.value", "2-A")
                        .param("loanDate.value", "2020-02-14"));

        mockMvc.perform(
                post("/returnbook/register")
                        .param("itemNumber.value", "2-A")
                        .param("returnDate.value", "2020-02-14"));

        Item itemInStock = itemDataSource.findItemInStock(new ItemNumber("2-A"));

        assertEquals("2-A", itemInStock.itemNumber().toString());
    }
}
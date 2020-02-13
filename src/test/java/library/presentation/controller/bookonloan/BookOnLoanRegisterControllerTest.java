package library.presentation.controller.bookonloan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookOnLoanRegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void 貸出が登録できる() throws Exception {
        mockMvc.perform(
                post("/bookonloan/register")
                        .param("memberNumber.value", "1")
                        .param("bookCollectionCode.value", "1-A")
                        .param("loanDate.value", "2011-11-11"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookonloan/register/completed?memberNumber=1"));
    }
}
package library.presentation.reservation;

import library.LibraryDBTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@LibraryDBTest
@AutoConfigureMockMvc
class RetentionControllerTest {
    @Autowired
    MockMvc mockMvc;

    // TODO 予約したその結果の取得をテストする
    @Test
    void 予約図書一覧画面を表示できる() throws Exception {
        mockMvc.perform(
                get("/retentions/requests"))
                .andExpect(status().isOk());
    }
}
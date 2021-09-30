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
public class EntrySearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void 本の検索画面が表示できる() throws Exception {
        mockMvc.perform(
                get("/reservation/materials/search"))
                .andExpect(status().isOk());
    }
}

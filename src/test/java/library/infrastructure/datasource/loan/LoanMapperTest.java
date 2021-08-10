package library.infrastructure.datasource.loan;

import library.domain.model.material.item.ItemNumber;
import library.domain.model.loan.Loan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * こういうこともできるという例
 * 推奨しているわけではない
 */
@SpringBootTest
class LoanMapperTest {

    @Autowired
    LoanMapper loanMapper;

    @Test
    void 所蔵品番号で貸出を取得できる() throws Exception {
        ItemNumber targetItem = new ItemNumber("1-A");
        Optional<Loan> loan = loanMapper.selectByItemNumber(targetItem);
        assertTrue(loan.isPresent());
    }
}
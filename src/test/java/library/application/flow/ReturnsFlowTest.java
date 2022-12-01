package library.application.flow;

import library.ScenarioTest;
import library.application.scenario.LoanScenario;
import library.application.scenario.ReturnsScenario;
import library.application.service.loan.LoanQueryService;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import library.infrastructure.datasource.member.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ScenarioTest
class ReturnsFlowTest {

    @Autowired
    LoanScenario loanScenario;

    @Autowired
    ReturnsScenario returnsScenario;

    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    MemberMapper memberMapper;

    @Test
    void 所蔵品を返却した際に貸出記録が消去される() {
        MemberNumber member = new MemberNumber(2);
        ItemNumber itemNumber = new ItemNumber("2-A");
        LoanRequest loanRequest = new LoanRequest(member, itemNumber, LoanDate.parse("2020-02-19"));
        loanScenario.loan(loanRequest);
        ReturnDate returnDate = ReturnDate.parse("2020-02-20");

        Returned returned = new Returned(itemNumber, returnDate);
        returnsScenario.returned(returned);

        List<Loan> loans = memberMapper.selectLoansByMemberNumber(member);

        assertTrue(loans.isEmpty());
    }
}

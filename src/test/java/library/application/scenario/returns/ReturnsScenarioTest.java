package library.application.scenario.returns;

import library.ScenarioTest;
import library.application.scenario.loan.LoanScenario;
import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.material.item.ItemStatus;
import library.domain.model.member.MemberNumber;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ScenarioTest
class ReturnsScenarioTest {

    @Autowired
    LoanScenario loanScenario;

    @Autowired
    ReturnsScenario returnsScenario;

    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    ItemQueryService itemQueryService;

    @Test
    void 返却する() {
        MemberNumber member = new MemberNumber(1);
        ItemNumber itemNumber = new ItemNumber("2-A");
        LoanRequest loanRequest = new LoanRequest(member, itemNumber, LoanDate.parse("2020-02-19"));
        loanScenario.loan(loanRequest);
        ReturnDate returnDate = ReturnDate.parse("2020-02-20");

        Returned returned = new Returned(itemNumber, returnDate);

        returnsScenario.returned(returned);

        ItemStatus itemStatus = itemQueryService.status(loanRequest.itemNumber());

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> loanQueryService.findBy(new ItemNumber("2-A"))),
                () -> assertEquals(itemStatus, ItemStatus.在庫中)
        );
    }

}

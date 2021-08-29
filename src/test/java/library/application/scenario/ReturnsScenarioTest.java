package library.application.scenario;

import library.LibraryDBTest;
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

@LibraryDBTest
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
    void 借りた所蔵品を返却することができる() {
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

    // @Test
    void 貸出記録が消去される() {
    }
}
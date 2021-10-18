package library.application.service.returns;

import library.LibraryDBTest;
import library.application.scenario.LoanScenario;
import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanRequest;
import library.domain.model.material.item.ItemNumber;
import library.domain.model.member.MemberNumber;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class ReturnEntryRecordServiceTest {
    @Autowired
    LoanScenario loanScenario;

    @Autowired
    ReturnMaterialRecordService returnMaterialRecordService;

    @Autowired
    LoanQueryService loanQueryService;

    @Autowired
    LoanRecordService loanRecordService;

    @Autowired
    MemberQueryService memberQueryService;

    @Test
    void 返却を登録できる() {
        LoanRequest loanRequest =
                generate(1, "1-A", "2020-02-20");
        loanScenario.loan(loanRequest);

        ItemNumber itemNumber = new ItemNumber("1-A");
        ReturnDate returnDate = ReturnDate.parse("2020-02-20");

        Returned returned = new Returned(itemNumber, returnDate);
        returnMaterialRecordService.returned(returned);

        assertThrows(IllegalArgumentException.class, () -> loanQueryService.findBy(new ItemNumber("1-A")));
    }

    private LoanRequest generate(int memberNumber, String itemNumber, String loanDate) {
        MemberNumber member = new MemberNumber(memberNumber);
        ItemNumber item = new ItemNumber(itemNumber);
        return new LoanRequest(member, item, LoanDate.parse(loanDate));
    }
}
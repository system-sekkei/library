package library.application.service.returns;

import library.LibraryDBTest;
import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.material.collection.ItemNumber;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

@LibraryDBTest
class ReturnMaterialRecordServiceTest {
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
        ItemNumber itemNumber = new ItemNumber("1-A");
        ReturnDate returnDate = ReturnDate.parse("2020-02-20");

        Returned returned = new Returned(itemNumber, returnDate);
        returnMaterialRecordService.returned(returned);

        assertThrows(IllegalArgumentException.class, () -> loanQueryService.findBy(new ItemNumber("1-A")));
    }
}
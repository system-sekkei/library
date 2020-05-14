package library.application.coordinator.bookonloan;

import library.LibraryDBTest;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.holding.HoldingQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.rule.BookOnLoanRequest;
import library.domain.model.loan.rule.LoaningCard;
import library.domain.model.book.item.ItemNumber;
import library.domain.model.book.item.ItemInStock;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.type.date.Date;
import library.infrastructure.datasource.bookonloan.RegisterBookOnLoanException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@LibraryDBTest
class BookOnLoanRegisterCoordinatorTest {
    @Autowired
    BookOnLoanRegisterCoordinator bookOnLoanRegisterCoordinator;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    HoldingQueryService holdingQueryService;

    @Autowired
    BookOnLoanQueryService bookOnLoanQueryService;

    @Test
    void 図書を貸し出すことができる() {
        BookOnLoanRequest bookOnLoanRequest =
                generate(1, "2-A", "2020-02-20");
        LoaningCard loaningCard = bookOnLoanRegisterCoordinator.loaning(bookOnLoanRequest);

        assertTrue(loaningCard.ok());
    }

    @Test
    void 貸出中の蔵書は貸し出すことができない() {
        BookOnLoanRequest bookOnLoanRequest =
                generate(2, "2-B", new LoanDate(Date.now()).toString());
        bookOnLoanRegisterCoordinator.loaning(bookOnLoanRequest);

        assertThrows(RegisterBookOnLoanException.class, () -> {
            LoaningCard loaning = bookOnLoanRegisterCoordinator.loaning(bookOnLoanRequest);
        });
    }

    @Test
    void 貸出制限冊数を超える会員には図書を貸し出すことができない() {
        List<String> list = List.of("2-C", "2-D", "2-E", "2-F", "2-G");
        for (String code : list) {
            BookOnLoanRequest bookOnLoanRequest =
                    generate(3, code, "2020-02-20");
            bookOnLoanRegisterCoordinator.loaning(bookOnLoanRequest);
        }

        BookOnLoanRequest bookOnLoanRequest =
                generate(3, "2-H", "2020-02-20");

        LoaningCard loaningCard = bookOnLoanRegisterCoordinator.loaning(bookOnLoanRequest);

        assertTrue(loaningCard.rejected());
    }

    private BookOnLoanRequest generate(int memberNumber, String itemNumber, String loanDate) {
        Member member = memberQueryService.findMember(new MemberNumber(memberNumber));
        ItemInStock itemInStock = holdingQueryService.findHoldingInStock(new ItemNumber(itemNumber));
        return new BookOnLoanRequest(member, itemInStock, new LoanDate(Date.from(loanDate)));
    }
}
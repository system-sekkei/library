package library.presentation.controller.bookonloan;

import library.application.coordinator.bookonloan.BookOnLoanRegisterCoordinator;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.holding.HoldingQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.rule.BookOnLoanRequest;
import library.domain.model.loan.rule.LoaningCard;
import library.domain.model.loan.rule.MemberAllBookOnLoans;
import library.domain.model.item.HoldingInStock;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 貸出図書の登録
 */
@Controller
@RequestMapping("bookonloan/register")
public class BookOnLoanRegisterController {
    BookOnLoanRecordService bookOnLoanRecordService;
    BookOnLoanRegisterCoordinator bookOnLoanRegisterCoordinator;
    BookOnLoanQueryService bookOnLoanQueryService;
    MemberQueryService memberQueryService;
    HoldingQueryService holdingQueryService;

    public BookOnLoanRegisterController(BookOnLoanRecordService bookOnLoanRecordService, BookOnLoanRegisterCoordinator bookOnLoanRegisterCoordinator, BookOnLoanQueryService bookOnLoanQueryService, MemberQueryService memberQueryService, HoldingQueryService holdingQueryService) {
        this.bookOnLoanRecordService = bookOnLoanRecordService;
        this.bookOnLoanRegisterCoordinator = bookOnLoanRegisterCoordinator;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.memberQueryService = memberQueryService;
        this.holdingQueryService = holdingQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("loaningOfBookForm", new LoaningOfBookForm());
        return "bookonloan/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("loaningOfBookForm") LoaningOfBookForm loaningOfBookForm, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) return "bookonloan/register/form";

        Member member = memberQueryService.findMember(loaningOfBookForm.memberNumber);
        HoldingInStock holdingInStock = holdingQueryService.findHoldingInStock(loaningOfBookForm.itemNumber);
        BookOnLoanRequest bookOnLoanRequest = new BookOnLoanRequest(member, holdingInStock, loaningOfBookForm.loanDate);

        LoaningCard loaningCard = bookOnLoanRegisterCoordinator.loaning(bookOnLoanRequest);

        if (loaningCard.rejected()) {
            result.addError(new ObjectError("error", loaningCard.message()));
            return "bookonloan/register/form";
        }

        attributes.addAttribute("memberNumber", bookOnLoanRequest.member().memberNumber());
        return "redirect:/bookonloan/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model, @RequestParam("memberNumber") MemberNumber memberNumber) {
        Member member = memberQueryService.findMember(memberNumber);
        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(member);
        model.addAttribute("memberAllBookOnLoans", memberAllBookOnLoans);
        return "bookonloan/register/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "memberNumber.value",
                "itemNumber.value",
                "loanDate.value"
        );
    }
}

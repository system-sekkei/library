package library.presentation.controller.bookonloan;

import library.application.coordinator.bookonloan.BookOnLoanRegisterCoordinator;
import library.application.coordinator.bookonloan.BookOnLoanValidResult;
import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookonloan.LoaningOfBookCollection;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
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
    BookCollectionQueryService bookCollectionQueryService;

    public BookOnLoanRegisterController(BookOnLoanRecordService bookOnLoanRecordService, BookOnLoanRegisterCoordinator bookOnLoanRegisterCoordinator, BookOnLoanQueryService bookOnLoanQueryService, MemberQueryService memberQueryService, BookCollectionQueryService bookCollectionQueryService) {
        this.bookOnLoanRecordService = bookOnLoanRecordService;
        this.bookOnLoanRegisterCoordinator = bookOnLoanRegisterCoordinator;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.memberQueryService = memberQueryService;
        this.bookCollectionQueryService = bookCollectionQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("loaningOfBookForm", new LoaningOfBookForm());
        return "bookonloan/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("loaningOfBookForm") LoaningOfBookForm loaningOfBookForm, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) return "bookonloan/register/form";

        // TODO: コーディネーターにまとめる
        Member member = memberQueryService.findMember(loaningOfBookForm.memberNumber);
        BookCollection bookCollection = bookCollectionQueryService.findBookCollection(loaningOfBookForm.bookCollectionCode);
        LoaningOfBookCollection loaningOfBookCollection = new LoaningOfBookCollection(member, bookCollection, loaningOfBookForm.loanDate);

        BookOnLoanValidResult valid = bookOnLoanRegisterCoordinator.isValid(loaningOfBookCollection);

        if (valid.hasError()) {
            result.addError(new ObjectError("error", valid.message()));
            return "bookonloan/register/form";
        }

        bookOnLoanRecordService.registerBookOnLoan(loaningOfBookCollection);

        attributes.addAttribute("memberNumber", loaningOfBookCollection.member().memberNumber());
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
                "bookCollectionCode.value",
                "loanDate.value"
        );
    }
}

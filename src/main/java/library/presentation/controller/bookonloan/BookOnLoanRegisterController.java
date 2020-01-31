package library.presentation.controller.bookonloan;

import library.application.coordinator.bookonloan.BookOnLoanQueryCoordinator;
import library.application.coordinator.bookonloan.BookOnLoanRegisterCoordinator;
import library.application.coordinator.bookonloan.BookOnLoanValidResult;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
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
    BookOnLoanQueryCoordinator bookOnLoanQueryCoordinator;

    public BookOnLoanRegisterController(
        BookOnLoanRecordService bookOnLoanRecordService,
        BookOnLoanRegisterCoordinator bookOnLoanRegisterCoordinator,
        BookOnLoanQueryCoordinator bookOnLoanQueryCoordinator) {
        this.bookOnLoanRecordService = bookOnLoanRecordService;
        this.bookOnLoanRegisterCoordinator = bookOnLoanRegisterCoordinator;
        this.bookOnLoanQueryCoordinator = bookOnLoanQueryCoordinator;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("bookOnLoan", BookOnLoan.blank());
        return "bookonloan/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("bookOnLoan") BookOnLoan bookOnLoan, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) return "bookonloan/register/form";

        BookOnLoanValidResult valid = bookOnLoanRegisterCoordinator.isValid(bookOnLoan);

        if (valid.hasError()) {
            result.addError(new ObjectError("error", valid.message()));
            return "bookonloan/register/form";
        }

        bookOnLoanRecordService.registerBookOnLoan(bookOnLoan);

        attributes.addAttribute("memberNumber", bookOnLoan.memberNumber());
        return "redirect:/bookonloan/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model, @RequestParam("memberNumber") MemberNumber memberNumber) {
        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryCoordinator.findMemberAllBookOnLoans(memberNumber);
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

package library.presentation.controller.bookonloan;

import library.application.coordinator.bookonloan.BookOnLoanRegisterCoordinator;
import library.application.coordinator.bookonloan.BookOnLoanValidResult;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.domain.model.bookonloan.BookOnLoan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 貸出図書の登録
 */
@Controller
@RequestMapping("bookonloan/register")
public class BookOnLoanRegisterController {
    BookOnLoanRecordService bookOnLoanRecordService;
    BookOnLoanRegisterCoordinator bookOnLoanRegisterCoordinator;

    public BookOnLoanRegisterController(
        BookOnLoanRecordService bookOnLoanRecordService,
        BookOnLoanRegisterCoordinator bookOnLoanRegisterCoordinator) {
        this.bookOnLoanRecordService = bookOnLoanRecordService;
        this.bookOnLoanRegisterCoordinator = bookOnLoanRegisterCoordinator;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("bookOnLoan", BookOnLoan.blank());
        return "bookonloan/register/form";
    }

    @PostMapping
    String register(@ModelAttribute("bookOnLoan") BookOnLoan bookOnLoan, BindingResult result) {
        BookOnLoanValidResult valid = bookOnLoanRegisterCoordinator.isValid(bookOnLoan);

        if (valid.hasError()) return "bookonloan/register/form";

        bookOnLoanRecordService.registerBookOnLoan(bookOnLoan);

        return "redirect:/bookonloan/register";
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

package library.presentation.controller.bookonloan;

import library.application.coordinator.bookonloan.BookOnLoanRegisterCoordinator;
import library.application.coordinator.bookonloan.BookOnLoanValidResult;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.domain.model.bookonloan.BookOnLoanRegister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        model.addAttribute("bookOnLoanRegister", BookOnLoanRegister.blank());
        return "bookonloan/register/form";
    }

    @PostMapping
    String register(@ModelAttribute("bookOnLoanRegister") BookOnLoanRegister bookOnLoanRegister, BindingResult result) {
        BookOnLoanValidResult valid = bookOnLoanRegisterCoordinator.isValid(bookOnLoanRegister);

        if (valid.hasError()) return "bookonloan/register/form";

        bookOnLoanRecordService.registerBookOnLoan(bookOnLoanRegister);

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

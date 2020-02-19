package library.presentation.controller.returnbook;

import library.application.coordinator.returnbook.ReturnBookCoordinator;
import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.member.MemberQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 貸出図書返却の登録
 */
@Controller
@RequestMapping("returnbook/register")
public class ReturnBookRegisterController {
    ReturnBookCoordinator returnBookCoordinator;
    BookOnLoanQueryService bookOnLoanQueryService;
    MemberQueryService memberQueryService;

    public ReturnBookRegisterController(ReturnBookCoordinator returnBookCoordinator, BookOnLoanQueryService bookOnLoanQueryService, MemberQueryService memberQueryService) {
        this.returnBookCoordinator = returnBookCoordinator;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.memberQueryService = memberQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("returnBookForm", new ReturnBookForm());
        return "returnbook/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("returnBookForm") ReturnBookForm returnBookForm, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) return "returnbook/register/form";

        returnBookCoordinator.returnBook(returnBookForm.bookCollectionCode, returnBookForm.returnDate);

        return "redirect:/returnbook/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model) {
        return "returnbook/register/completed";
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

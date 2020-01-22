package library.presentation.controller.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookonloan.BookOnLoanRegister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 貸出図書の登録
 */
@Controller
@RequestMapping("bookonloan/register")
public class BookOnLoanRegisterController {
    MemberQueryService memberQueryService;
    BookCollectionQueryService bookCollectionQueryService;
    BookOnLoanRecordService bookOnLoanRecordService;

    public BookOnLoanRegisterController(
        MemberQueryService memberQueryService,
        BookCollectionQueryService bookCollectionQueryService,
        BookOnLoanRecordService bookOnLoanRecordService) {
        this.memberQueryService = memberQueryService;
        this.bookCollectionQueryService = bookCollectionQueryService;
        this.bookOnLoanRecordService = bookOnLoanRecordService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("bookOnLoanRegister", BookOnLoanRegister.blank());
        return "bookonloan/register/form";
    }

    @PostMapping
    String register(@ModelAttribute("boolOnLoanRegister") BookOnLoanRegister bookOnLoanRegister) {
        // FIXME:
        System.out.println(bookOnLoanRegister.memberNumber());
        System.out.println(bookOnLoanRegister.bookCollectionCode());
        System.out.println(bookOnLoanRegister.loanDate());

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

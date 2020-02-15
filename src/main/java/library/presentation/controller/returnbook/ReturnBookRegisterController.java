package library.presentation.controller.returnbook;

import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.bookonloan.ReturnBookRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
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
    ReturnBookRecordService returnBookRecordService;
    BookOnLoanQueryService bookOnLoanQueryService;
    MemberQueryService memberQueryService;

    public ReturnBookRegisterController(ReturnBookRecordService returnBookRecordService, BookOnLoanQueryService bookOnLoanQueryService, MemberQueryService memberQueryService) {
        this.returnBookRecordService = returnBookRecordService;
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

        BookOnLoan bookOnLoan = null;  // TODO:

        returnBookRecordService.registerReturnBook(bookOnLoan, returnBookForm.returnDate);

        attributes.addAttribute("memberNumber", bookOnLoan.member().memberNumber());
        return "redirect:/returnbook/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model, @RequestParam("memberNumber") MemberNumber memberNumber) {
        Member member = memberQueryService.findMember(memberNumber);
        MemberAllBookOnLoans memberAllBookOnLoans = bookOnLoanQueryService.findMemberAllBookOnLoans(member);
        model.addAttribute("memberAllBookOnLoans", memberAllBookOnLoans);
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

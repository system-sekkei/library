package library.presentation.controller.returnbook;

import library.application.service.loan.LoanQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.returns.ReturnBookRecordService;
import library.domain.model.item.ItemNumber;
import library.domain.model.loan.returned.ReturnDate;
import library.domain.model.loan.returned.Returned;
import library.domain.type.date.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 返却の登録画面
 */
@Controller
@RequestMapping("returnbook/register")
public class ReturnBookRegisterController {
    ReturnBookRecordService returnBookRecordService;
    MemberQueryService memberQueryService;

    public ReturnBookRegisterController(ReturnBookRecordService returnBookRecordService, LoanQueryService loanQueryService, MemberQueryService memberQueryService) {
        this.returnBookRecordService = returnBookRecordService;
        this.memberQueryService = memberQueryService;
    }

    @GetMapping
    String init(Model model) {
        Returned returned = new Returned(new ItemNumber(""), new ReturnDate(Date.now()));
        model.addAttribute("returned", returned );
        return "returnbook/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("returned") Returned returned,
                    BindingResult result) {
        if (result.hasErrors()) return "returnbook/register/form";

        returnBookRecordService.registerReturnBook(returned);

        return "redirect:/returnbook/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model) {
        return "returnbook/register/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "itemNumber.value",
                "returnDate.value"
        );
    }
}

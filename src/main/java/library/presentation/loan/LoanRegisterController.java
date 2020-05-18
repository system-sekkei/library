package library.presentation.loan;

import library.application.coordinator.loan.LoanCoordinator;
import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRegisterService;
import library.application.service.item.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.item.Item;
import library.domain.model.loan.loan.LoanRequest;
import library.domain.model.loan.rule.*;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 貸出の登録画面
 */
@Controller
@RequestMapping("loan/register")
public class LoanRegisterController {
    LoanRegisterService loanRegisterService;
    LoanCoordinator loanCoordinator;
    LoanQueryService loanQueryService;
    MemberQueryService memberQueryService;
    ItemQueryService itemQueryService;

    public LoanRegisterController(LoanRegisterService loanRegisterService, LoanCoordinator loanCoordinator, LoanQueryService loanQueryService, MemberQueryService memberQueryService, ItemQueryService itemQueryService) {
        this.loanRegisterService = loanRegisterService;
        this.loanCoordinator = loanCoordinator;
        this.loanQueryService = loanQueryService;
        this.memberQueryService = memberQueryService;
        this.itemQueryService = itemQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("loaningOfBookForm", new LoaningOfBookForm());
        return "loan/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("loaningOfBookForm") LoaningOfBookForm loaningOfBookForm,
                    BindingResult result,
                    RedirectAttributes attributes) {
        if (result.hasErrors()) return "loan/register/form";

        Member member = memberQueryService.findMember(loaningOfBookForm.memberNumber);

        if (member == null) {
            result.addError(new FieldError(result.getObjectName(), "memberNumber.value", "この番号の会員はいません"));
            return "loan/register/form";
        }

        Item itemInStock = itemQueryService.findItemInStock(loaningOfBookForm.itemNumber);
        LoanRequest loanRequest = new LoanRequest(member, itemInStock, loaningOfBookForm.loanDate);

        RestrictionResult restrictionResult = loanCoordinator.shouldRestrict(loanRequest);

        if (restrictionResult != RestrictionResult.貸出可能) {
            result.addError(new ObjectError("error", restrictionResult.message()));
            return "loan/register/form";
        }

        loanCoordinator.loan(loanRequest);

        attributes.addAttribute("memberNumber", loanRequest.member().number().toString());
        return "redirect:/loan/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model, @ModelAttribute("memberNumber") MemberNumber memberNumber) {
        Member member = memberQueryService.findMember(memberNumber);
        LoanStatus loanStatus = loanQueryService.loanStatusOf(member);
        model.addAttribute("loanStatus", loanStatus);
        return "loan/register/completed";
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

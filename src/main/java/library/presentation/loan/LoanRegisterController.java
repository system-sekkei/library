package library.presentation.loan;

import library.application.coordinator.loan.LoanCoordinator;
import library.application.service.item.ItemQueryService;
import library.application.service.loan.LoanQueryService;
import library.application.service.loan.LoanRegisterService;
import library.application.service.member.MemberQueryService;
import library.domain.model.loan.loan.LoanRequest;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.rule.RestrictionResult;
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

import static library.domain.model.loan.rule.RestrictionResult.貸出可能;

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

    public LoanRegisterController(LoanRegisterService loanRegisterService, LoanCoordinator loanCoordinator, LoanQueryService loanQueryService, MemberQueryService memberQueryService) {
        this.loanRegisterService = loanRegisterService;
        this.loanCoordinator = loanCoordinator;
        this.loanQueryService = loanQueryService;
        this.memberQueryService = memberQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("loanRequest", new LoanRequest());
        return "loan/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("loanRequest") LoanRequest loanRequest,
                    BindingResult result,
                    RedirectAttributes attributes) {
        if (result.hasErrors()) return "loan/register/form";

        Member member = memberQueryService.findMember(loanRequest.memberNumber());

        if (member == null) {
            result.addError(new FieldError(result.getObjectName(), "memberNumber.value", "この番号の会員はいません"));
            return "loan/register/form";
        }

        RestrictionResult restrictionResult = loanCoordinator.shouldRestrict(loanRequest);

        if (restrictionResult != 貸出可能) {
            result.addError(new ObjectError("error", restrictionResult.message()));
            return "loan/register/form";
        }

        loanCoordinator.loan(loanRequest);

        attributes.addFlashAttribute("member", loanRequest.memberNumber());
        return "redirect:/loan/register/completed";
    }

    @GetMapping("completed")
    String completed(@ModelAttribute("member") MemberNumber memberNumber, Model model) {
        LoanStatus loanStatus = loanQueryService.loanStatusOf(memberNumber);
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

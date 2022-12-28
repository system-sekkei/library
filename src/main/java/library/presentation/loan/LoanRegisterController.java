package library.presentation.loan;

import library.application.scenario.loan.LoanScenario;
import library.domain.model.loan.rule.ItemLoanability;
import library.domain.model.loan.LoanRequest;
import library.domain.model.loan.rule.LoanStatus;
import library.domain.model.loan.rule.Loanability;
import library.domain.model.member.MemberStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static library.domain.model.loan.rule.Loanability.貸出可能;
import static library.domain.model.member.MemberStatus.未登録;

/**
 * 貸出の登録画面
 */
@Controller
@RequestMapping("loan/register")
public class LoanRegisterController {
    LoanScenario coordinator;

    public LoanRegisterController(LoanScenario coordinator) {
        this.coordinator = coordinator;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("loanRequest", LoanRequest.empty());
        return "loan/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("loanRequest") LoanRequest loanRequest, BindingResult bindingResult,
                    RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) return "loan/form";

        MemberStatus memberStatus = coordinator.memberStatus(loanRequest);
        if ( memberStatus == 未登録) {
            bindingResult.addError(
                    new FieldError(bindingResult.getObjectName(),
                    "memberNumber.value", "この番号の会員はいません"));
            return "loan/form";
        }

        ItemLoanability 貸出可能な所蔵品かどうか = coordinator.貸出可能な所蔵品かどうか(loanRequest.itemNumber());
        if (貸出可能な所蔵品かどうか != ItemLoanability.貸出可能) {
            bindingResult.addError(new ObjectError("error", 貸出可能な所蔵品かどうか.message()));
            return "loan/form";
        }

        Loanability loanability = coordinator.loanability(loanRequest);
        if (loanability != 貸出可能) {
            bindingResult.addError(new ObjectError("error", loanability.message()));
            return "loan/form";
        }

        coordinator.loan(loanRequest);
        LoanStatus loanStatus = coordinator.loanStatus(loanRequest);

        attributes.addFlashAttribute("status", loanStatus);
        return "redirect:/loan/register/completed";
    }

    @GetMapping("completed")
    String completed(@ModelAttribute("status") LoanStatus status, Model model) {
        model.addAttribute("loanStatus", status);
        return "loan/completed";
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

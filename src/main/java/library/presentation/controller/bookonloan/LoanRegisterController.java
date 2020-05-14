package library.presentation.controller.bookonloan;

import library.application.coordinator.bookonloan.LoanRegisterCoordinator;
import library.application.service.bookonloan.LoanQueryService;
import library.application.service.bookonloan.LoanRegisterService;
import library.application.service.holding.ItemQueryService;
import library.application.service.member.MemberQueryService;
import library.domain.model.book.item.Item;
import library.domain.model.loan.rule.LoanRequest;
import library.domain.model.loan.rule.LoaningCard;
import library.domain.model.loan.rule.MemberAllBookOnLoans;
import library.domain.model.member.Member;
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
public class LoanRegisterController {
    LoanRegisterService loanRegisterService;
    LoanRegisterCoordinator loanRegisterCoordinator;
    LoanQueryService loanQueryService;
    MemberQueryService memberQueryService;
    ItemQueryService itemQueryService;

    public LoanRegisterController(LoanRegisterService loanRegisterService, LoanRegisterCoordinator loanRegisterCoordinator, LoanQueryService loanQueryService, MemberQueryService memberQueryService, ItemQueryService itemQueryService) {
        this.loanRegisterService = loanRegisterService;
        this.loanRegisterCoordinator = loanRegisterCoordinator;
        this.loanQueryService = loanQueryService;
        this.memberQueryService = memberQueryService;
        this.itemQueryService = itemQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("loaningOfBookForm", new LoaningOfBookForm());
        return "bookonloan/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("loaningOfBookForm") LoaningOfBookForm loaningOfBookForm, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) return "bookonloan/register/form";

        Member member = memberQueryService.findMember(loaningOfBookForm.memberNumber);
        Item itemInStock = itemQueryService.findItemInStock(loaningOfBookForm.itemNumber);
        LoanRequest loanRequest = new LoanRequest(member, itemInStock, loaningOfBookForm.loanDate);

        LoaningCard loaningCard = loanRegisterCoordinator.loaning(loanRequest);

        if (loaningCard.rejected()) {
            result.addError(new ObjectError("error", loaningCard.message()));
            return "bookonloan/register/form";
        }

        attributes.addAttribute("memberNumber", loanRequest.member().memberNumber());
        return "redirect:/bookonloan/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model, @RequestParam("memberNumber") MemberNumber memberNumber) {
        Member member = memberQueryService.findMember(memberNumber);
        MemberAllBookOnLoans memberAllBookOnLoans = loanQueryService.findMemberAllBookOnLoans(member);
        model.addAttribute("memberAllBookOnLoans", memberAllBookOnLoans);
        return "bookonloan/register/completed";
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

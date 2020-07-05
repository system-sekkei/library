package library.presentation.returns;

import library.application.coordinator.loan.LoanCoordinator;
import library.domain.model.item.ItemNumber;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
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
@RequestMapping("returns/register")
public class ReturnBookRegisterController {
    LoanCoordinator loanCoordinator;

    public ReturnBookRegisterController(LoanCoordinator loanCoordinator) {
        this.loanCoordinator = loanCoordinator;
    }

    @GetMapping
    String init(Model model) {
        Returned returned = new Returned(new ItemNumber(""), ReturnDate.now());
        model.addAttribute("returned", returned );
        return "returns/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("returned") Returned returned,
                    BindingResult result) {
        if (result.hasErrors()) return "returns/register/form";

        loanCoordinator.returend(returned);

        return "redirect:/returns/register/completed";
    }

    @GetMapping("completed")
    String completed() {
        return "returns/register/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "itemNumber.value",
                "returnDate.value"
        );
    }
}

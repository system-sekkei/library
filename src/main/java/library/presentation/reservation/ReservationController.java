package library.presentation.reservation;

import library.application.scenario.ReservationScenario;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static library.domain.model.member.MemberStatus.未登録;

/**
 * 予約の登録画面
 */
@Controller
@RequestMapping("reservation/register")
public class ReservationController {
    ReservationScenario reservationScenario;

    public ReservationController(ReservationScenario reservationScenario) {
        this.reservationScenario = reservationScenario;
    }

    @GetMapping
    String redirectToSearch() {
        return "redirect:/reservation/entries/search";
    }

    @GetMapping(params = {"entry"})
    String reservationForm(@RequestParam("entry") EntryNumber entryNumber, Model model) {
        Entry entry = reservationScenario.findMaterial(entryNumber);
        model.addAttribute("entry", entry);
        model.addAttribute("member", MemberNumber.empty());
        return "reservation/form";
    }

    @PostMapping
    String register(
            @RequestParam("entry") EntryNumber entryNumber,
            @ModelAttribute("member") MemberNumber memberNumber,
            BindingResult bindingResult,
            Model model
            ) {

        Entry entry = reservationScenario.findMaterial(entryNumber);
        if (bindingResult.hasErrors()) {
            model.addAttribute("entry", entry);
            return "reservation/form";
        }

        if (reservationScenario.memberStatus(memberNumber) == 未登録) {
            model.addAttribute("member", memberNumber);
            model.addAttribute("entry", entry);
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),"value","その番号の会員はいません"));
            return "reservation/form";
        }

        reservationScenario.reserve(entry, memberNumber);

        return "redirect:/reservation/register/completed";
    }

    @GetMapping("completed")
    String completed() {
        return "reservation/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "member.value"
        );
    }
}

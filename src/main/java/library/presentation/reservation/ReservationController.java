package library.presentation.reservation;

import library.application.scenario.ReservationScenario;
import library.domain.model.book.bibliography.Book;
import library.domain.model.book.bibliography.BookNumber;
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
        return "redirect:/reservation/books/search";
    }

    @GetMapping(params = {"book"})
    String reservationForm(@RequestParam("book") BookNumber bookNumber, Model model) {
        Book book = reservationScenario.findBook(bookNumber);
        model.addAttribute("book", book);
        model.addAttribute("member", MemberNumber.empty());
        return "reservation/register/form";
    }

    @PostMapping
    String register(
            @RequestParam("book") BookNumber bookNumber,
            @ModelAttribute("member") MemberNumber memberNumber,
            BindingResult bindingResult,
            Model model
            ) {

        Book book = reservationScenario.findBook(bookNumber);
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "reservation/register/form";
        }

        if (reservationScenario.memberStatus(memberNumber) == 未登録) {
            model.addAttribute("member", memberNumber);
            model.addAttribute("book", book);
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),"value","その番号の会員はいません"));
            return "reservation/register/form";
        }

        reservationScenario.reserve(book, memberNumber);

        return "redirect:/reservation/register/completed";
    }

    @GetMapping("completed")
    String completed(Model model) {
        return "reservation/register/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "member.value"
        );
    }
}

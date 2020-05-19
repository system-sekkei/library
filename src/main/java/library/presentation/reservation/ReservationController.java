package library.presentation.reservation;

import library.application.service.book.BookQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.item.bibliography.Book;
import library.domain.model.item.bibliography.BookNumber;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import library.domain.model.reservation.reservation.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 貸出予約の登録画面
 */
@Controller
@RequestMapping("reservation/register")
public class ReservationController {
    ReservationRecordService reservationRecordService;
    MemberQueryService memberQueryService;
    BookQueryService bookQueryService;

    public ReservationController(ReservationRecordService reservationRecordService, MemberQueryService memberQueryService, BookQueryService bookQueryService) {
        this.reservationRecordService = reservationRecordService;
        this.memberQueryService = memberQueryService;
        this.bookQueryService = bookQueryService;
    }

    @GetMapping
    String redirectToSearch() {
        return "redirect:/reservation/books/search";
    }

    @GetMapping(params = {"book"})
    String init(@RequestParam("book") BookNumber bookNumber, Model model) {
        Book book = bookQueryService.findBook(bookNumber);
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

        Book book = bookQueryService.findBook(bookNumber);
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "reservation/register/form";
        }

        Member member = memberQueryService.findMember(memberNumber);

        if (member == null) {
            model.addAttribute("member", memberNumber);
            model.addAttribute("book", book);
            bindingResult.addError(new FieldError( bindingResult.getObjectName(),"value","その番号の会員はいません"));
            return "reservation/register/form";
        }

        Reservation tryingToReserveBook = Reservation.of(member, book);

        reservationRecordService.registerReservation(tryingToReserveBook);

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

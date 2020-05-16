package library.presentation.controller.reservation;

import library.application.service.member.MemberQueryService;
import library.application.service.book.BookQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.book.bibliography.Book;
import library.domain.model.member.Member;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservedBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 貸出予約の登録画面
 */
@Controller
@RequestMapping("reservation/register")
public class ReservationRegisterController {
    ReservationRecordService reservationRecordService;
    MemberQueryService memberQueryService;
    BookQueryService bookQueryService;

    public ReservationRegisterController(ReservationRecordService reservationRecordService, MemberQueryService memberQueryService, BookQueryService bookQueryService) {
        this.reservationRecordService = reservationRecordService;
        this.memberQueryService = memberQueryService;
        this.bookQueryService = bookQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("reservationForm", new ReservationForm());
        return "reservation/register/form";
    }

    @PostMapping
    String register(@Validated @ModelAttribute("reservationForm") ReservationForm reservationForm, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) return "reservation/register/form";

        Member member = memberQueryService.findMember(reservationForm.memberNumber);
        Book book = bookQueryService.findBook(reservationForm.bookNumber);
        Reservation tryingToReserveBook = new Reservation(member, new ReservedBook(book));

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
                "memberNumber.value",
                "bookId.value"
        );
    }
}

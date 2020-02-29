package library.presentation.controller.reservation;

import library.application.service.bookonloan.BookOnLoanQueryService;
import library.application.service.member.MemberQueryService;
import library.application.service.reservation.ReservationRecordService;
import library.domain.model.book.BookId;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 貸出予約の登録
 */
@Controller
@RequestMapping("reservation/register")
public class ReservationRegisterController {
    ReservationRecordService reservationRecordService;
    BookOnLoanQueryService bookOnLoanQueryService;
    MemberQueryService memberQueryService;

    public ReservationRegisterController(ReservationRecordService reservationRecordService, BookOnLoanQueryService bookOnLoanQueryService, MemberQueryService memberQueryService) {
        this.reservationRecordService = reservationRecordService;
        this.bookOnLoanQueryService = bookOnLoanQueryService;
        this.memberQueryService = memberQueryService;
    }

    @GetMapping
    String init(@PathVariable(value = "memberNumber") MemberNumber memberNumber, @PathVariable(value = "bookId") BookId bookId, Model model) {
        model.addAttribute("reservationForm", new ReservationForm());
        return "reservation/register/form";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "memberNumber.value",
                "book.value"
        );
    }
}

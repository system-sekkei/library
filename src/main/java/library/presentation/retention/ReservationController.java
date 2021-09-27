package library.presentation.retention;

import library.application.scenario.RetentionScenario;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.request.ReservationNumber;
import library.domain.model.reservation.request.Reservations;
import library.domain.model.retention.Retention;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 予約の管理画面
 */
@Controller("予約の管理")
@RequestMapping("retentions/requests")
public class ReservationController {
    RetentionScenario retentionScenario;

    public ReservationController(RetentionScenario retentionScenario) {
        this.retentionScenario = retentionScenario;
    }

    @GetMapping
    String requests(Model model) {
        Reservations reservations = retentionScenario.未準備の予約一覧();
        model.addAttribute("reservations", reservations);
        return "retention/requests";
    }

    @GetMapping("{reservationNumber}")
    String retentionForm(
            @PathVariable("reservationNumber") ReservationNumber reservationNumber,
            Model model) {
        Reservation reservation = retentionScenario.reservationOf(reservationNumber);
        model.addAttribute("reservation", reservation);
        model.addAttribute("retention", Retention.empty());
        return "retention/form";
    }

    @PostMapping("canceled")
    String cancel(@RequestParam("notAvailable") ReservationNumber reservationNumber){
        retentionScenario.cancel(reservationNumber);
        return "redirect:/retentions/requests";
    }
}

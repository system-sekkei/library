package library.presentation.retention;

import library.application.scenario.ReservationCancellationScenario;
import library.application.scenario.RetentionScenario;
import library.domain.model.reservation.Reservation;
import library.domain.model.reservation.ReservationNumber;
import library.domain.model.reservation.wait.ReservationWithWaitingOrderList;
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
    ReservationCancellationScenario reservationCancellationScenario;

    public ReservationController(RetentionScenario retentionScenario, ReservationCancellationScenario reservationCancellationScenario) {
        this.retentionScenario = retentionScenario;
        this.reservationCancellationScenario = reservationCancellationScenario;
    }

    @GetMapping
    String requests(Model model) {
        ReservationWithWaitingOrderList reservations = retentionScenario.未準備の予約一覧();
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
        reservationCancellationScenario.cancel(reservationNumber);
        return "redirect:/retentions/requests";
    }
}

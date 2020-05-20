package library.presentation.retention;

import library.application.coordinator.retention.RetentionCoordinator;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.retention.Retained;
import library.domain.model.reservation.retention.Retentions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 予約(取置依頼)の一覧画面
 */
@Controller
@RequestMapping("retentions")
public class RetentionController {
    RetentionCoordinator retentionCoordinator;

    public RetentionController(RetentionCoordinator retentionCoordinator) {
        this.retentionCoordinator = retentionCoordinator;
    }

    @GetMapping("requests")
    String requests(Model model) {
        Reservations reservations = retentionCoordinator.reservations();
        model.addAttribute("reservations", reservations);
        return "retention/requests";
    }

    @GetMapping
    String retentions(Model model) {
        Retentions retentions = retentionCoordinator.retentions();
        model.addAttribute("retentions", retentions);
        return "retention/retentions";
    }

    @GetMapping("requests/{reservationNumber}")
    String retentionForm(
            @PathVariable("reservationNumber") ReservationNumber reservationNumber,
            Model model) {
        Reservation reservation = retentionCoordinator.reservationOf(reservationNumber);
        model.addAttribute("reservation", reservation);
        return "retention/form";
    }

    @PostMapping
    String retain(Retained retained, @RequestBody String body) {
        System.out.println(body);
        System.out.println(retained);

        return "redirect:/retentions/requests";
    }
}

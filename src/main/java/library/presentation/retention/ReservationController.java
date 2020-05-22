package library.presentation.retention;

import library.application.coordinator.retention.RetentionCoordinator;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.retention.Retention;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 予約の管理画面
 */
@Controller("予約の管理")
@RequestMapping("retentions/requests")
public class ReservationController {
    RetentionCoordinator retentionCoordinator;

    public ReservationController(RetentionCoordinator retentionCoordinator) {
        this.retentionCoordinator = retentionCoordinator;
    }

    @GetMapping
    String requests(Model model) {
        Reservations reservations = retentionCoordinator.reservations();
        model.addAttribute("reservations", reservations);
        return "retention/requests";
    }

    @GetMapping("{reservationNumber}")
    String retentionForm(
            @PathVariable("reservationNumber") ReservationNumber reservationNumber,
            Model model) {
        Reservation reservation = retentionCoordinator.reservationOf(reservationNumber);
        model.addAttribute("reservation", reservation);
        model.addAttribute("retention", new Retention());
        return "retention/form";
    }
}

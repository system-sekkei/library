package library.presentation.controller.reservation;

import library.application.coordinator.retention.RetentionCoordinator;
import library.domain.model.reservation.retention.RetentionableReservations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 貸出図書一覧
 */
@Controller
@RequestMapping("reservation/list")
public class ReservationListController {
    RetentionCoordinator retentionCoordinator;

    public ReservationListController(RetentionCoordinator retentionCoordinator) {
        this.retentionCoordinator = retentionCoordinator;
    }

    @GetMapping
    String init(Model model) {
        // TODO: 在庫ありの本のみにするかどうかは画面側で切り替えられるようにしたい
        RetentionableReservations retentionableReservations = retentionCoordinator.retention();
        model.addAttribute("reservations", retentionableReservations);
        return "reservation/list";
    }
}

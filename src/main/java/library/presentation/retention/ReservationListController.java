package library.presentation.retention;

import library.application.coordinator.retention.RetentionCoordinator;
import library.domain.model.reservation.reservation.Reservations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 貸出図書の一覧画面
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
        Reservations reservations = retentionCoordinator.retention();
        model.addAttribute("reservations", reservations);
        return "reservation/list";
    }

}

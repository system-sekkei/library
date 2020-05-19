package library.presentation.retention;

import library.application.coordinator.retention.RetentionCoordinator;
import library.domain.model.reservation.reservation.Reservations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 予約(取置依頼)の一覧画面
 */
@Controller
@RequestMapping("retentions/requests")
public class RetentionController {
    RetentionCoordinator retentionCoordinator;

    public RetentionController(RetentionCoordinator retentionCoordinator) {
        this.retentionCoordinator = retentionCoordinator;
    }

    @GetMapping
    String init(Model model) {
        // TODO: 在庫ありの本のみにするかどうかは画面側で切り替えられるようにしたい
        Reservations reservations = retentionCoordinator.reservations();
        System.out.println(reservations);
        model.addAttribute("reservations", reservations);
        return "retention/list";
    }

}

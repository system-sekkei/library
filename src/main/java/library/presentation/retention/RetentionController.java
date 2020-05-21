package library.presentation.retention;

import library.application.coordinator.retention.RetentionCoordinator;
import library.domain.model.item.ItemStatus;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.reservation.Reservations;
import library.domain.model.reservation.retention.Retention;
import library.domain.model.reservation.retention.RetainedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static library.domain.model.item.ItemStatus.貸出可能;

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

    @GetMapping("requests/{reservationNumber}")
    String retentionForm(
            @PathVariable("reservationNumber") ReservationNumber reservationNumber,
            Model model) {
        Reservation reservation = retentionCoordinator.reservationOf(reservationNumber);
        model.addAttribute("reservation", reservation);
        model.addAttribute("retention", new Retention());
        return "retention/form";
    }

    @PostMapping
    String retain(@Validated @ModelAttribute("retention") Retention retention, BindingResult bindingResult,
                  Model model) {

        Reservation reservation = retentionCoordinator.reservationOf(retention.reservationNumber());
        model.addAttribute("reservation", reservation);

        if (bindingResult.hasErrors()) {
            return "retention/form";
        }

        if (!retentionCoordinator.isSameBook(reservation, retention)) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", "予約された本の蔵書ではありません"));
            return "retention/form";
        }

        ItemStatus itemStatus = retentionCoordinator.itemStatus(retention.itemNumber());
        if (itemStatus != 貸出可能) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", itemStatus.description()));
            return "retention/form";
        }

        retentionCoordinator.retain(retention);

        return "redirect:/retentions/requests";
    }

    /**
     * 取置の一覧
     */
    @GetMapping
    String retentions(Model model) {
        RetainedList retainedList = retentionCoordinator.retentions();
        System.out.println(retainedList);
        model.addAttribute("retainedList", retainedList);
        return "retention/retentions";
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "itemNumber.value",
                "reservationNumber.value"
        );
    }
}

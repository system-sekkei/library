package library.presentation.retention;

import library.application.coordinator.retention.RetentionCoordinator;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.ItemStatus;
import library.domain.model.reservation.reservation.Reservation;
import library.domain.model.reservation.reservation.ReservationNumber;
import library.domain.model.reservation.retention.RetainedList;
import library.domain.model.reservation.retention.Retention;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import static library.domain.model.item.ItemStatus.未登録;
import static library.domain.model.item.ItemStatus.貸出可能;

/**
 * 取置の管理画面
 */
@Controller("取置の管理")
@RequestMapping("retentions")
public class RetentionController {
    RetentionCoordinator retentionCoordinator;

    public RetentionController(RetentionCoordinator retentionCoordinator) {
        this.retentionCoordinator = retentionCoordinator;
    }

    @GetMapping
    String retainedList(Model model) {
        RetainedList retainedList = retentionCoordinator.retainedList();
        model.addAttribute("retainedList", retainedList);
        return "retention/retentions";
    }

    @PostMapping
    String retain(@Validated @ModelAttribute("retention") Retention retention, BindingResult bindingResult,
                  Model model) {

        Reservation reservation = retentionCoordinator.reservationOf(retention.reservationNumber());
        model.addAttribute("reservation", reservation);

        if (bindingResult.hasErrors()) {
            return "retention/form";
        }

        ItemStatus itemStatus = retentionCoordinator.itemStatus(retention.itemNumber());

        if (itemStatus == 未登録) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", itemStatus.description()));
            return "retention/form";
        }

        if (!retentionCoordinator.isSameBook(reservation, retention)) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", "予約された本ではありません"));
            return "retention/form";
        }

        if (itemStatus != 貸出可能) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", itemStatus.description()));
            return "retention/form";
        }

        retentionCoordinator.retain(retention);

        return "redirect:/retentions";
    }

    @PostMapping(value = "loans", params = {"loaned"})
    String loan(@RequestParam("loaned") ItemNumber itemNumber) {
        retentionCoordinator.loan(itemNumber);
        return "redirect:/retentions";
    }

    @PostMapping(value = "loans", params = {"expired"})
    String expired(@RequestParam("expired")ItemNumber itemNumber) {
        retentionCoordinator.expire(itemNumber);
        return "redirect:/retentions";
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "itemNumber.value",
                "reservationNumber.value"
        );
    }
}
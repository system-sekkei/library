package library.presentation.retention;

import library.application.scenario.RetentionScenario;
import library.domain.model.item.ItemNumber;
import library.domain.model.item.ItemStatus;
import library.domain.model.reservation.request.Reservation;
import library.domain.model.reservation.retention.BookMatching;
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
import static library.domain.model.reservation.retention.BookMatching.不一致;

/**
 * 取置の管理画面
 */
@Controller("取置の管理")
@RequestMapping("retentions")
public class RetentionController {
    RetentionScenario retentionScenario;

    public RetentionController(RetentionScenario retentionScenario) {
        this.retentionScenario = retentionScenario;
    }

    @GetMapping
    String retainedList(Model model) {
        RetainedList retainedList = retentionScenario.retainedList();
        model.addAttribute("retainedList", retainedList);
        return "retention/retentions";
    }

    @PostMapping
    String retain(@Validated @ModelAttribute("retention") Retention retention, BindingResult bindingResult,
                  Model model) {

        Reservation reservation = retentionScenario.reservationOf(retention.reservationNumber());
        model.addAttribute("reservation", reservation);

        if (bindingResult.hasErrors()) {
            return "retention/form";
        }

        ItemStatus itemStatus = retentionScenario.itemStatus(retention.itemNumber());

        if (itemStatus == 未登録) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", itemStatus.description()));
            return "retention/form";
        }

        BookMatching matching = retentionScenario.isSameBook(reservation, retention);
        if (matching == 不一致) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", matching.description()));
            return "retention/form";
        }

        if (itemStatus != 貸出可能) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "itemNumber.value", itemStatus.description()));
            return "retention/form";
        }

        retentionScenario.retain(retention);

        return "redirect:/retentions/requests";
    }

    @PostMapping(value = "loans", params = {"loaned"})
    String loan(@RequestParam("loaned") ItemNumber itemNumber) {
        retentionScenario.loan(itemNumber);
        return "redirect:/retentions";
    }

    @PostMapping(value = "loans", params = {"expired"})
    String expired(@RequestParam("expired")ItemNumber itemNumber) {
        retentionScenario.expire(itemNumber);
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
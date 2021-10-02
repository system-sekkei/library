package library.presentation.reservation;

import library.application.scenario.ReservationScenario;
import library.domain.model.material.entry.Keyword;
import library.domain.model.material.instock.EntryInStockList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 所蔵品目の検索画面
 */
@Controller
@RequestMapping("reservation/entries")
public class EntrySearchController {
    ReservationScenario reservationScenario;

    public EntrySearchController(ReservationScenario reservationScenario) {
        this.reservationScenario = reservationScenario;
    }

    @GetMapping("search")
    String search(Model model, @ModelAttribute("searchKeyword") Keyword searchKeyword, BindingResult result) {
        EntryInStockList entryInStockList = reservationScenario.search(searchKeyword);
        model.addAttribute("materialInStockList", entryInStockList);
        model.addAttribute("searchKeyword", searchKeyword);
        return "reservation/search";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "searchKeyword.value"
        );
    }
}

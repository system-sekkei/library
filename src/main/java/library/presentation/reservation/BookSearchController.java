package library.presentation.reservation;

import library.application.coordinator.reservation.ReservationCoordinator;
import library.domain.model.item.bibliography.Keyword;
import library.domain.model.reservation.availability.BookAvailabilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 本の検索画面
 */
@Controller
@RequestMapping("reservation/books")
public class BookSearchController {
    ReservationCoordinator reservationCoordinator;

    public BookSearchController(ReservationCoordinator reservationCoordinator) {
        this.reservationCoordinator = reservationCoordinator;
    }

    @GetMapping("search")
    String search(Model model, @ModelAttribute("searchKeyword") Keyword searchKeyword, BindingResult result) {
        BookAvailabilities availabilities = reservationCoordinator.search(searchKeyword);
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("searchKeyword", searchKeyword);
        return "reservation/books/search";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "searchKeyword.value"
        );
    }
}

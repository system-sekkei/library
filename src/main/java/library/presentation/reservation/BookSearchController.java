package library.presentation.reservation;

import library.application.service.book.BookQueryService;
import library.domain.model.item.bibliography.Keyword;
import library.domain.model.item.bibliography.Books;
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
    BookQueryService bookQueryService;

    public BookSearchController(BookQueryService bookQueryService) {
        this.bookQueryService = bookQueryService;
    }

    @GetMapping("search")
    String search(Model model, @ModelAttribute("searchKeyword") Keyword searchKeyword, BindingResult result) {
        BookAvailabilities books = bookQueryService.search(searchKeyword);

        model.addAttribute("books", books);
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

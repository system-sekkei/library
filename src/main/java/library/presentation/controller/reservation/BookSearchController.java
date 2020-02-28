package library.presentation.controller.reservation;

import library.application.service.reservation.BookQueryService;
import library.domain.model.book.Books;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 本検索
 */
@Controller
@RequestMapping("reservation/books")
public class BookSearchController {
    BookQueryService bookQueryService;

    public BookSearchController(BookQueryService bookQueryService) {
        this.bookQueryService = bookQueryService;
    }

    @GetMapping
    String init(Model model) {
        model.addAttribute("books", new Books(List.of()));
        model.addAttribute("bookSearchForm", new BookSearchForm());
        return "reservation/books/list";
    }

    @GetMapping("search")
    String search(Model model, @Validated @ModelAttribute("bookSearchForm") BookSearchForm bookSearchForm) {
        Books books = bookQueryService.search(bookSearchForm.searchKeyword);

        model.addAttribute("books", books);
        model.addAttribute("searchKeyword", bookSearchForm.searchKeyword);
        return "reservation/books/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "searchKeyword.value"
        );
    }
}

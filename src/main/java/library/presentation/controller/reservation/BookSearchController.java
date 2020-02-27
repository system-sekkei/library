package library.presentation.controller.reservation;

import library.application.service.reservation.BookQueryService;
import library.domain.model.book.BookSearchKeyword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("searchKeyword", new BookSearchKeyword(""));
        return "reservation/books/list";
    }
}

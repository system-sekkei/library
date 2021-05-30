package library.presentation.returns;

import library.application.service.returns.ReturnBookRecordService;
import library.domain.model.book.collection.ItemNumber;
import library.domain.model.returned.ReturnDate;
import library.domain.model.returned.Returned;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 返却の登録画面
 */
@Controller
@RequestMapping("returns")
public class ReturnBookController {
    ReturnBookRecordService returnBookRecordService;

    public ReturnBookController(ReturnBookRecordService returnBookRecordService) {
        this.returnBookRecordService = returnBookRecordService;
    }

    @GetMapping
    String init(Model model) {
        Returned returned = new Returned(new ItemNumber(""), ReturnDate.now());
        model.addAttribute("returned", returned );
        return "returns/form";
    }

    @PostMapping
    String returned(@Validated @ModelAttribute("returned") Returned returned,
                    BindingResult result) {
        if (result.hasErrors()) return "returns/form";

        returnBookRecordService.returned(returned);

        return "redirect:/returns/completed";
    }

    @GetMapping("completed")
    String completed() {
        return "returns/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "itemNumber.value",
                "returnDate.value"
        );
    }
}

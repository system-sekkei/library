package library.presentation.returns;

import library.application.scenario.ReturnsScenario;
import library.domain.model.material.item.ItemNumber;
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
public class ReturnMaterialController {

    ReturnsScenario returnsScenario;

    public ReturnMaterialController(ReturnsScenario returnsScenario) {
        this.returnsScenario = returnsScenario;
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

        returnsScenario.returned(returned);

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

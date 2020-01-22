package library.presentation.controller.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookcollection.BookCollectionRecordService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 貸出図書の登録
 */
@Controller
@RequestMapping("bookonloan/register")
public class BookOnLoanRegisterController {
    MemberQueryService memberQueryService;
    BookCollectionQueryService bookCollectionQueryService;
    BookOnLoanRecordService bookOnLoanRecordService;
    BookCollectionRecordService bookCollectionRecordService;

    public BookOnLoanRegisterController(
        MemberQueryService memberQueryService,
        BookCollectionQueryService bookCollectionQueryService,
        BookOnLoanRecordService bookOnLoanRecordService,
        BookCollectionRecordService bookCollectionRecordService) {
        this.memberQueryService = memberQueryService;
        this.bookCollectionQueryService = bookCollectionQueryService;
        this.bookOnLoanRecordService = bookOnLoanRecordService;
        this.bookCollectionRecordService = bookCollectionRecordService;
    }

    @GetMapping
    String init(Model model) {
        return "bookonloan/register/form";
    }
}

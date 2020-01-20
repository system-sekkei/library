package library.presentation.controller.bookonloan;

import library.application.service.bookcollection.BookCollectionQueryService;
import library.application.service.bookcollection.BookCollectionRecordService;
import library.application.service.bookonloan.BookOnLoanRecordService;
import library.application.service.member.MemberQueryService;
import library.domain.model.member.Member;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 貸出図書の登録
 */
@Controller
@RequestMapping("bookonloan/{memberNumber}/register")
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

    @ModelAttribute("member")
    Member member(@PathVariable(value = "memberNumber") String memberNumber) { // TODO: MemberNumberにする
        return memberQueryService.findMember(new MemberNumber(Integer.parseInt(memberNumber)));
    }

    @GetMapping
    String init(Model model) {
        return "bookonloan/register/form";
    }
}

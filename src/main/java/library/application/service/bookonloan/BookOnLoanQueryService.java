package library.application.service.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.BookOnLoans;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Service;

/**
 * 貸出図書参照サービス
 */
@Service
public class BookOnLoanQueryService {
    BookOnLoanRepository bookOnLoanRepository;

    BookOnLoanQueryService(BookOnLoanRepository bookOnLoanRepository) {
        this.bookOnLoanRepository = bookOnLoanRepository;
    }

    public BookOnLoans findMemberAllBookOnLoans(MemberNumber memberNumber) {
        return bookOnLoanRepository.findMemberAllBookOnLoans(memberNumber);
    }
}
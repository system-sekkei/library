package library.infrastructure.datasource.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.BookOnLoans;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookOnLoanDataSource implements BookOnLoanRepository {
    BookOnLoanMapper mapper;

    public BookOnLoanDataSource(BookOnLoanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void registerBookOnLoan(BookOnLoan bookOnLoan) {
        Integer bookOnLoanId = mapper.newBookOnLoanIdentifier();
        mapper.insertBookOnLoan(
            bookOnLoanId,
            bookOnLoan.memberNumber(),
            bookOnLoan.bookCollectionCode(),
            bookOnLoan.loanDate());
    }

    @Override
    public BookOnLoans findMemberAllBookOnLoans(MemberNumber memberNumber) {
        List<BookOnLoan> bookOnLoans = mapper.selectByMemberNumber(memberNumber);
        return new BookOnLoans(bookOnLoans);
    }

}

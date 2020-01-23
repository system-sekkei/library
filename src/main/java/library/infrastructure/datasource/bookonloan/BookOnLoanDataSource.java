package library.infrastructure.datasource.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookonloan.BookOnLoanRegister;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.member.MemberNumber;
import org.springframework.stereotype.Repository;

@Repository
public class BookOnLoanDataSource implements BookOnLoanRepository {
    BookOnLoanMapper mapper;

    public BookOnLoanDataSource(BookOnLoanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void registerBookOnLoan(BookOnLoanRegister bookOnLoanRegister) {
        Integer bookOnLoanId = mapper.newBookOnLoanIdentifier();
        mapper.insertBookOnLoan(
            bookOnLoanId,
            bookOnLoanRegister.memberNumber(),
            bookOnLoanRegister.bookCollectionCode(),
            bookOnLoanRegister.loanDate());
    }

    @Override
    public MemberAllBookOnLoans findMemberAllBookOnLoans(MemberNumber memberNumber) {
        return null;
    }

}

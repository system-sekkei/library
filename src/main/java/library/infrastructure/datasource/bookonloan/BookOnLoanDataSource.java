package library.infrastructure.datasource.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.BookOnLoans;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.member.Member;
import library.infrastructure.datasource.bookcollection.BookCollectionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookOnLoanDataSource implements BookOnLoanRepository {
    BookOnLoanMapper mapper;
    BookCollectionMapper bookCollectionMapper;

    public BookOnLoanDataSource(BookOnLoanMapper mapper, BookCollectionMapper bookCollectionMapper) {
        this.mapper = mapper;
        this.bookCollectionMapper = bookCollectionMapper;
    }

    @Override
    public void registerBookOnLoan(BookOnLoan bookOnLoan) {
        Integer bookOnLoanId = mapper.newBookOnLoanIdentifier();
        mapper.insertBookOnLoan(
                bookOnLoanId,
                bookOnLoan.member().memberNumber(),
                bookOnLoan.bookCollection().bookCollectionCode(),
                bookOnLoan.loanDate());
    }

    @Override
    public void registerReturnBook(BookOnLoan bookOnLoan) {
        // TODO:
    }

    @Override
    public MemberAllBookOnLoans findMemberAllBookOnLoans(Member member) {
        List<BookOnLoanData> bookOnLoanDataList = mapper.selectByMemberNumber(member.memberNumber());
        List<BookOnLoan> bookOnLoans = bookOnLoans(member, bookOnLoanDataList);

        return new MemberAllBookOnLoans(member, new BookOnLoans(bookOnLoans));
    }

    List<BookOnLoan> bookOnLoans(Member member, List<BookOnLoanData> bookOnLoanDataList) {
        if (bookOnLoanDataList.isEmpty()) return List.of();

        List<BookCollectionCode> bookCollectionCodes =
                bookOnLoanDataList.stream()
                        .map(bookOnLoanData -> bookOnLoanData.bookCollectionCode)
                        .collect(Collectors.toList());
        List<BookCollection> bookCollections = bookCollectionMapper.selectBookCollections(bookCollectionCodes);

        return bookOnLoanDataList.stream()
                .map(bookOnLoanData ->
                        bookCollections.stream()
                                .filter(bookCollection -> bookCollection.bookCollectionCode().sameValue(bookOnLoanData.bookCollectionCode))
                                .findFirst()
                                .map(bookCollection -> new BookOnLoan(member, bookCollection, bookOnLoanData.loanDate))
                                .orElseThrow())
                .collect(Collectors.toList());
    }


}

package library.infrastructure.datasource.bookonloan;

import library.application.repository.BookOnLoanRepository;
import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.loan.BookOnLoan;
import library.domain.model.bookonloan.loan.BookOnLoans;
import library.domain.model.bookonloan.loaning.BookOnLoanRequest;
import library.domain.model.bookonloan.loaning.MemberAllBookOnLoans;
import library.domain.model.bookonloan.returning.ReturningBookOnLoan;
import library.domain.model.member.Member;
import library.infrastructure.datasource.bookcollection.BookCollectionMapper;
import library.infrastructure.datasource.member.MemberMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookOnLoanDataSource implements BookOnLoanRepository {
    BookOnLoanMapper mapper;
    BookCollectionMapper bookCollectionMapper;
    MemberMapper memberMapper;

    public BookOnLoanDataSource(BookOnLoanMapper mapper, BookCollectionMapper bookCollectionMapper, MemberMapper memberMapper) {
        this.mapper = mapper;
        this.bookCollectionMapper = bookCollectionMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public BookOnLoan registerBookOnLoan(BookOnLoanRequest bookOnLoanRequest) {
        BookCollectionCode bookCollectionCode = bookOnLoanRequest.bookCollection().bookCollectionCode();
        bookCollectionMapper.getBookCollectionCodeWithLock(bookCollectionCode);

        BookCollection bookCollection = bookCollectionMapper.selectBookCollection(bookCollectionCode);

        if (bookCollection.bookCollectionStatus().unLoanable()) {
            throw new RegisterBookOnLoanException(bookOnLoanRequest);
        }

        Integer bookOnLoanId = mapper.newBookOnLoanIdentifier();
        mapper.insertBookOnLoan(
                bookOnLoanId,
                bookOnLoanRequest.member().memberNumber(),
                bookOnLoanRequest.bookCollection().bookCollectionCode(),
                bookOnLoanRequest.loanDate());

        return findBookOnLoanByBookCollectionCode(bookCollectionCode);
    }

    @Override
    public void registerReturnBook(ReturningBookOnLoan returningBookOnLoan) {
        BookOnLoan bookOnLoan = returningBookOnLoan.bookOnLoan();
        mapper.insertReturnBook(bookOnLoan.bookOnLoanId(), returningBookOnLoan.returnDate());
    }

    @Override
    public MemberAllBookOnLoans findMemberAllBookOnLoans(Member member) {
        List<BookOnLoanData> bookOnLoanDataList = mapper.selectByMemberNumber(member.memberNumber());
        List<BookOnLoan> bookOnLoans = bookOnLoans(member, bookOnLoanDataList);

        return new MemberAllBookOnLoans(member, new BookOnLoans(bookOnLoans));
    }

    @Override
    public BookOnLoan findBookOnLoanByBookCollectionCode(BookCollectionCode bookCollectionCode) {
        BookOnLoanData bookOnLoanData = mapper.selectByBookCollectionCode(bookCollectionCode);

        if (bookOnLoanData == null) {
            throw new IllegalArgumentException(String.format("現在貸し出されていない蔵書です。蔵書コード：%s", bookCollectionCode));
        }

        Member member = memberMapper.selectMember(bookOnLoanData.memberNumber);
        BookOnLoan bookOnLoan = bookOnLoans(member, List.of(bookOnLoanData)).get(0);
        return bookOnLoan;
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
                                .map(bookCollection -> new BookOnLoan(bookOnLoanData.bookOnLoanId, member, bookCollection, bookOnLoanData.loanDate))
                                .orElseThrow())
                .collect(Collectors.toList());
    }


}

package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.loan.BookOnLoanId;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.member.MemberNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookOnLoanMapper {
    Integer newBookOnLoanIdentifier();

    List<BookOnLoanData> selectByMemberNumber(@Param("memberNumber") MemberNumber memberNumber);

    void insertBookOnLoan(
            @Param("bookOnLoanId") Integer bookOnLoanId,
            @Param("memberNumber") MemberNumber memberNumber,
            @Param("bookCollectionCode") BookCollectionCode bookCollectionCode,
            @Param("loanDate") LoanDate loanDate);

    void insertReturnBook(
            @Param("bookOnLoanId") BookOnLoanId bookOnLoanId,
            @Param("returnDate") ReturnDate returnDate);

    BookOnLoanData selectByBookCollectionCode(@Param("bookCollectionCode") BookCollectionCode bookCollectionCode);
}

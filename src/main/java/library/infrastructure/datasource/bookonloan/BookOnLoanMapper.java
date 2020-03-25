package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookonloan.loan.BookOnLoanId;
import library.domain.model.bookonloan.loan.LoanDate;
import library.domain.model.bookonloan.returning.ReturnDate;
import library.domain.model.holding.HoldingCode;
import library.domain.model.member.MemberNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookOnLoanMapper {
    Integer newBookOnLoanIdentifier();

    List<BookOnLoanData> selectByMemberNumber(@Param("memberNumber") MemberNumber memberNumber);

    void insertBookOnLoan(
            @Param("bookOnLoanId") Integer bookOnLoanId,
            @Param("memberNumber") MemberNumber memberNumber,
            @Param("holdingCode") HoldingCode holdingCode,
            @Param("loanDate") LoanDate loanDate);

    void insertReturnBook(
            @Param("bookOnLoanId") BookOnLoanId bookOnLoanId,
            @Param("returnDate") ReturnDate returnDate);

    Optional<BookOnLoanData> selectByHoldingCode(@Param("holdingCode") HoldingCode holdingCode);

    List<BookOnLoanData> selectByHoldingCodes(@Param("holdingCodes") List<HoldingCode> holdingCodes);

    List<ReturnBookData> selectReturnedBookByHoldingCodes(@Param("holdingCodes") List<HoldingCode> holdingCodes);
}

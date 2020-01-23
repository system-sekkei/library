package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.BookOnLoan;
import library.domain.model.bookonloan.LoanDate;
import library.domain.model.member.MemberNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookOnLoanMapper {
    Integer newBookOnLoanIdentifier();

    List<BookOnLoan> selectByMemberNumber(@Param("memberNumber") MemberNumber memberNumber);

    void insertBookOnLoan(
        @Param("bookOnLoanId") Integer bookOnLoanId,
        @Param("memberNumber") MemberNumber memberNumber,
        @Param("bookCollectionCode") BookCollectionCode bookCollectionCode,
        @Param("loanDate") LoanDate loanDate);
}

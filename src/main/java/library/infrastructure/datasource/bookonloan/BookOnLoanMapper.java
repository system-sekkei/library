package library.infrastructure.datasource.bookonloan;

import library.domain.model.bookcollection.BookCollectionCode;
import library.domain.model.bookonloan.BookOnLoanRegister;
import library.domain.model.bookonloan.LoanDate;
import library.domain.model.bookonloan.MemberAllBookOnLoans;
import library.domain.model.member.MemberNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookOnLoanMapper {
    Integer newBookOnLoanIdentifier();

    MemberAllBookOnLoans findMemberAllBookOnLoans(@Param("memberNumber") MemberNumber memberNumber);

    void insertBookOnLoan(
        @Param("bookOnLoanId") Integer bookOnLoanId,
        @Param("memberNumber") MemberNumber memberNumber,
        @Param("bookCollectionCode") BookCollectionCode bookCollectionCode,
        @Param("loanDate") LoanDate loanDate);
}

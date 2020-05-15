package library.infrastructure.datasource.loan;

import library.domain.model.book.item.ItemNumber;
import library.domain.model.loan.loan.Loan;
import library.domain.model.loan.loan.LoanDate;
import library.domain.model.loan.loan.LoanNumber;
import library.domain.model.loan.returned.ReturnDate;
import library.domain.model.loan.returned.Returned;
import library.domain.model.member.MemberNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
    int newLoanNumber();

    List<Loan> selectByMemberNumber(@Param("memberNumber") MemberNumber memberNumber);

    void insertLoan(
            @Param("loanNumber") int loanNumber,
            @Param("memberNumber") MemberNumber memberNumber,
            @Param("itemNumber") ItemNumber itemNumber,
            @Param("loanDate") LoanDate loanDate);

    void insertReturnBook(
            @Param("loanNumber") LoanNumber loanNumber,
            @Param("returned") Returned returned);

    Optional<Loan> selectByItemNumber(@Param("itemNumber") ItemNumber itemNumber);

    List<Loan> selectByItemNumbers(@Param("itemNumbers") List<ItemNumber> itemNumbers);

    List<Returned> selectReturnedByItemNumbers(@Param("itemNumbers") List<ItemNumber> itemNumbers);
}

package library.infrastructure.datasource.loan;

import library.domain.model.material.collection.ItemNumber;
import library.domain.model.loan.Loan;
import library.domain.model.loan.LoanDate;
import library.domain.model.loan.LoanNumber;
import library.domain.model.returned.Returned;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
    int newLoanNumber();

    void insertLoan(
            @Param("loanNumber") int loanNumber,
            @Param("itemNumber") ItemNumber itemNumber,
            @Param("loanDate") LoanDate loanDate);

    void insertReturnMaterial(
            @Param("loanNumber") LoanNumber loanNumber,
            @Param("returned") Returned returned);

    Optional<Loan> selectByItemNumber(@Param("itemNumber") ItemNumber itemNumber);

    List<Loan> selectByItemNumbers(@Param("itemNumbers") List<ItemNumber> itemNumbers);

    List<Returned> selectReturnedByItemNumbers(@Param("itemNumbers") List<ItemNumber> itemNumbers);
}

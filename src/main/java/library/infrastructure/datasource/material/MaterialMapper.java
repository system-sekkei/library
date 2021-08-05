package library.infrastructure.datasource.material;

import library.domain.model.material.Material;
import library.domain.model.material.MaterialNumber;
import library.domain.model.material.bibliography.Keyword;
import library.domain.model.reservation.loanability.MaterialLoanability;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMapper {
    List<MaterialLoanability> searchMaterials(
            @Param("keyword") Keyword keyword,
            @Param("limit") int limit
    );

    Material findMaterial(MaterialNumber materialNumber);
}

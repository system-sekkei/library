package library.infrastructure.datasource.material;

import library.application.service.material.MaterialRepository;
import library.domain.model.material.entry.Material;
import library.domain.model.material.entry.MaterialNumber;
import library.domain.model.material.entry.NumberOfMaterial;
import library.domain.model.material.entry.*;
import library.domain.model.reservation.loanability.MaterialLoanabilities;
import library.domain.model.reservation.loanability.MaterialLoanability;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialDataSource implements MaterialRepository {
    MaterialMapper materialMapper;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public MaterialDataSource(MaterialMapper materialMapper) {
        this.materialMapper = materialMapper;
    }

    @Override
    public MaterialLoanabilities search(Keyword keyword) {
        List<MaterialLoanability> materials = materialMapper.searchMaterials(keyword, NumberOfMaterial.MAX_TO_SHOW + 1);
        return new MaterialLoanabilities(materials);
    }

    @Override
    public Material findMaterial(MaterialNumber materialNumber) {
        return materialMapper.findMaterial(materialNumber);
    }
}

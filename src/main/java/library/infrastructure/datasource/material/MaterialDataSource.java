package library.infrastructure.datasource.material;

import library.application.service.material.MaterialRepository;
import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.NumberOfEntry;
import library.domain.model.material.entry.*;
import library.domain.model.material.instock.MaterialInStockList;
import library.domain.model.material.instock.MaterialInStock;
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
    public MaterialInStockList search(Keyword keyword) {
        List<MaterialInStock> materials = materialMapper.searchMaterials(keyword, NumberOfEntry.MAX_TO_SHOW + 1);
        return new MaterialInStockList(materials);
    }

    @Override
    public Entry findMaterial(EntryNumber entryNumber) {
        return materialMapper.findMaterial(entryNumber);
    }
}

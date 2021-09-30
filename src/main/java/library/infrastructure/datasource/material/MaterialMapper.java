package library.infrastructure.datasource.material;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.Keyword;
import library.domain.model.material.instock.MaterialInStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMapper {
    List<MaterialInStock> searchMaterials(
            @Param("keyword") Keyword keyword,
            @Param("limit") int limit
    );

    Entry findMaterial(EntryNumber entryNumber);
}

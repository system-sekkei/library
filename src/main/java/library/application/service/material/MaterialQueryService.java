package library.application.service.material;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.Keyword;
import library.domain.model.material.instock.MaterialInStockList;
import org.springframework.stereotype.Service;

/**
 * 資料の参照と検索
 */
@Service
public class MaterialQueryService {
    MaterialRepository materialRepository;

    MaterialQueryService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    /**
     * 資料番号で本を見つける
     */
    public Entry findMaterial(EntryNumber entryNumber) {
        return materialRepository.findMaterial(entryNumber);
    }

    /**
     * キーワードで本を探す
     */
    public MaterialInStockList search(Keyword keyword) {
        return materialRepository.search(keyword);
    }
}

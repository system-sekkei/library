package library.application.service.material;

import library.domain.model.material.Material;
import library.domain.model.material.MaterialNumber;
import library.domain.model.material.bibliography.Keyword;
import library.domain.model.reservation.loanability.MaterialLoanabilities;
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
    public Material findMaterial(MaterialNumber materialNumber) {
        return materialRepository.findMaterial(materialNumber);
    }

    /**
     * キーワードで本を探す
     */
    public MaterialLoanabilities search(Keyword keyword) {
        return materialRepository.search(keyword);
    }
}

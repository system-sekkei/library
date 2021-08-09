package library.application.service.material;

import library.domain.model.material.entry.Material;
import library.domain.model.material.entry.MaterialNumber;
import library.domain.model.material.entry.Keyword;
import library.domain.model.reservation.loanability.MaterialLoanabilities;

/**
 * 本リポジトリ
 */
public interface MaterialRepository {

    MaterialLoanabilities search(Keyword keyword);

    Material findMaterial(MaterialNumber materialNumber);
}

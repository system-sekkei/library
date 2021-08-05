package library.application.service.material;

import library.domain.model.material.Material;
import library.domain.model.material.MaterialNumber;
import library.domain.model.material.bibliography.Keyword;
import library.domain.model.reservation.loanability.MaterialLoanabilities;

/**
 * 本リポジトリ
 */
public interface MaterialRepository {

    MaterialLoanabilities search(Keyword keyword);

    Material findMaterial(MaterialNumber materialNumber);
}

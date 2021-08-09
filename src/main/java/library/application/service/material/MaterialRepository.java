package library.application.service.material;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.Keyword;
import library.domain.model.reservation.loanability.MaterialLoanabilities;

/**
 * 本リポジトリ
 */
public interface MaterialRepository {

    MaterialLoanabilities search(Keyword keyword);

    Entry findMaterial(EntryNumber entryNumber);
}

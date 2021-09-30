package library.application.service.material;

import library.domain.model.material.entry.Entry;
import library.domain.model.material.entry.EntryNumber;
import library.domain.model.material.entry.Keyword;
import library.domain.model.material.instock.MaterialInStockList;

/**
 * 本リポジトリ
 */
public interface MaterialRepository {

    MaterialInStockList search(Keyword keyword);

    Entry findMaterial(EntryNumber entryNumber);
}

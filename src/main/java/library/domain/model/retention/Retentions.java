package library.domain.model.retention;

import library.domain.model.bookcollection.BookCollection;

import java.util.List;

/**
 * 取置のリスト
 */
public class Retentions {
    List<RetainedBookCollection> list;

    public boolean notContains(BookCollection bookCollection) {
        return list.stream().noneMatch(retainedBookCollection -> retainedBookCollection.isA(bookCollection));
    }
}

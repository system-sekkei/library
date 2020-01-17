package library.domain.model.retention;

import library.domain.model.bookcollection.BookCollection;

/**
 * 取置
 */
public class Retention {
    RetainedDate retainedDate;
    BookCollection bookCollection;

    public RetentionDeadline retentionDeadline() {
        return RetentionDeadline.deadline(retainedDate);
    }
}

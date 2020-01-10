package library.domain.model;

import library.domain.type.date.Date;

/**
 * 取置期限
 */
public class RetentionDeadline {
    Date value;

    public RetentionDeadline(Date value) {
        this.value = value;
    }
}

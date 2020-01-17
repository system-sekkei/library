package library.domain.model.retention;

import library.domain.type.date.Date;

/**
 * 取置日
 */
public class RetainedDate {
    Date value;

    public RetainedDate(Date value) {
        this.value = value;
    }

    public Date value() {
        return value;
    }
}

package library.domain.model.loan.returned;

import library.domain.type.date.Date;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 返却日
 */
public class ReturnDate {
    @Valid
    @NotNull(message = "返却日を入力してください")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate value;

    @Deprecated
    ReturnDate() {
    }

    private ReturnDate(LocalDate value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public static ReturnDate parse(String dateText) {
        return new ReturnDate(LocalDate.parse(dateText));
    }
    public static ReturnDate now() {
        return new ReturnDate(LocalDate.now());
    }
}

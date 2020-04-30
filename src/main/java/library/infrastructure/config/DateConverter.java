package library.infrastructure.config;

import library.domain.type.date.Date;
import org.springframework.core.convert.converter.Converter;

/**
 * 文字列 -> 日付 型変換
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source.isEmpty()) return null;

        return Date.from(source);
    }
}

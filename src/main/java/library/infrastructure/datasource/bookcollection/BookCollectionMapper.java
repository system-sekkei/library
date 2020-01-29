package library.infrastructure.datasource.bookcollection;

import library.domain.model.bookcollection.BookCollection;
import library.domain.model.bookcollection.BookCollectionCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookCollectionMapper {
    BookCollection selectBookCollection(@Param("bookCollectionCode") BookCollectionCode bookCollectionCode);
}

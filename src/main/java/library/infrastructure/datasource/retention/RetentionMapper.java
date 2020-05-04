package library.infrastructure.datasource.retention;

import library.domain.model.item.ItemNumber;
import library.domain.model.reservation.reservation.ReservationId;
import library.domain.model.retention.RetainedDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RetentionMapper {

    void insertRetainedHolding(
            @Param("reservationId") ReservationId reservationId,
            @Param("itemNumber") ItemNumber itemNumber,
            @Param("retainedDate") RetainedDate retainedDate);
}

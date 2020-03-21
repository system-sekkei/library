package library.infrastructure.datasource.retention;

import library.domain.model.holding.HoldingCode;
import library.domain.model.reservation.reservation.ReservationId;
import library.domain.model.retention.RetainedDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RetentionMapper {

    void insertRetainedHolding(
            @Param("reservationId") ReservationId reservationId,
            @Param("holdingCode") HoldingCode holdingCode,
            @Param("retainedDate") RetainedDate retainedDate);
}

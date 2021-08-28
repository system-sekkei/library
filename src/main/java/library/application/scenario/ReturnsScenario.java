package library.application.scenario;

import library.application.service.returns.ReturnMaterialRecordService;
import library.domain.model.returned.Returned;
import org.springframework.stereotype.Service;

/**
 * 返却シナリオ
 */
@Service
public class ReturnsScenario {
    ReturnMaterialRecordService returnMaterialRecordService;

    public ReturnsScenario(ReturnMaterialRecordService returnMaterialRecordService) {
        this.returnMaterialRecordService = returnMaterialRecordService;
    }

    /**
     * 返却する
     */
    public void returned(Returned returned) {
        this.returnMaterialRecordService.returned(returned);
    }
}

package library.application.service.retention;

import library.application.repository.RetentionRepository;
import library.domain.model.reservation.retention.Retentions;
import org.springframework.stereotype.Service;

@Service
public class RetentionQueryService {
    RetentionRepository retentionRepository;

    public RetentionQueryService(RetentionRepository retentionRepository) {
        this.retentionRepository = retentionRepository;
    }

    public Retentions retentions() {
        return retentionRepository.retentions();
    }
}

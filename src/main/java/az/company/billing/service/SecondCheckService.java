package az.company.billing.service;

import az.company.billing.dto.SecondCheckRequest;
import az.company.billing.dto.SecondCheckResponse;
import az.company.billing.entities.PremiumEntity;
import az.company.billing.process.Parser;
import az.company.billing.repositories.IssueRepo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SecondCheckService {

    private static final Logger LOGGER = Logger.getLogger(SecondCheckService.class);
    private final IssueRepo issueRepo;

    public SecondCheckService(IssueRepo issueRepo) {
        this.issueRepo = issueRepo;
    }

    void secondCheckRequestMethod(List<PremiumEntity> PREMIUM_ENTITY_LIST, SecondCheckRequest secondCheckRequest) {
        LOGGER.info("secondCheckRequestMethod() is started...");
        String url = "http://localhost:9090/secondCheckResponseXml";
        secondCheckResponseMethod(PREMIUM_ENTITY_LIST, Objects.requireNonNull(Parser.secondCheckResponseEntity(url, secondCheckRequest).getBody()));
        LOGGER.info("secondCheckRequestMethod() is stopped.");
    }

    private void secondCheckResponseMethod(List<PremiumEntity> PREMIUM_ENTITY_LIST, SecondCheckResponse secondCheckResponse) {

        LOGGER.info("secondCheckResponseMethod() is started...");
        for (int i = 0; i < secondCheckResponse.getPayments().size(); i++) {
            if (!PREMIUM_ENTITY_LIST.get(i).getRRN().equals(secondCheckResponse.getPayments().get(i).getRRN())
                    && !PREMIUM_ENTITY_LIST.get(i).getAmount().equals(secondCheckResponse.getPayments().get(i).getAmount())) {
                LOGGER.warn("AzeriCard side: " + PREMIUM_ENTITY_LIST.get(i) + ": : Bank side: " + secondCheckResponse.getPayments().get(i).toString());
            } else {
                LOGGER.info("everything is okay");
            }
        }
    }
}

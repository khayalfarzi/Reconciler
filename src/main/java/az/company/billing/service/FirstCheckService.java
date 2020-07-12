package az.company.billing.service;

import az.company.billing.dto.FirstCheckRequest;
import az.company.billing.dto.FirstCheckResponse;
import az.company.billing.dto.SecondCheckRequest;
import az.company.billing.dto.Status;
import az.company.billing.entities.IssueEntity;
import az.company.billing.entities.PremiumEntity;
import az.company.billing.exceptions.NoDataFoundException;
import az.company.billing.exceptions.UnexpectedValueException;
import az.company.billing.process.Parser;
import az.company.billing.repositories.IssueRepo;
import az.company.billing.repositories.PremiumRepo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FirstCheckService {

    private static final Logger LOGGER = Logger.getLogger(FirstCheckService.class);
    private static List<PremiumEntity> PREMIUM_ENTITY_LIST = new ArrayList<>();
    private final PremiumRepo premiumRepo;
    private final IssueRepo issueRepo;


    public FirstCheckService(PremiumRepo premiumRepo, IssueRepo issueRepo) {
        this.premiumRepo = premiumRepo;
        this.issueRepo = issueRepo;
    }

    public void firstCheckRequestMethod() {
        System.out.println("firstCheckRequestMethod() started");

        LOGGER.info("firstCheckRequestMethod() started...");
        final LocalDate DATE = LocalDate.now().minus(Period.ofDays(1));
        String url = "http://localhost:9090/firstCheckResponseXml";
        Double totalA = premiumRepo.getTotalAmount(DATE);
        PREMIUM_ENTITY_LIST = premiumRepo.getAllByTransactionDateAndStatus(DATE, Status.OK.name());

        if (!PREMIUM_ENTITY_LIST.isEmpty()) {
            FirstCheckRequest firstCheckRequestEntity = FirstCheckRequest
                    .builder()
                    .trType("0")
                    .firstRRN(PREMIUM_ENTITY_LIST.get(0).getRRN())
                    .lastRRN(PREMIUM_ENTITY_LIST.get(PREMIUM_ENTITY_LIST.size() - 1).getRRN())
                    .totalOperCount(String.format("%s", PREMIUM_ENTITY_LIST.size()))
                    .totalAmount(totalA)
                    .build();

            FirstCheckResponse firstCheckResponse = (FirstCheckResponse) Parser.objectResponseEntity(url, firstCheckRequestEntity).getBody();
            firstCheckResponseMethod(Objects.requireNonNull(firstCheckResponse));
        } else {
            LOGGER.warn("Daily transfer not founded.");
            throw new NoDataFoundException("No daily transfer founded.");
        }
        LOGGER.info("firstCheckRequestMethod() ended.");

        System.out.println("firstCheckRequestMethod() ended");
        System.out.println();
    }

    private void firstCheckResponseMethod(FirstCheckResponse firstCheckResponse) {
        System.out.println("firstCheckResponseMethod() is started...");

        LOGGER.info("firstCheckResponseMethod() is started...");
        if (firstCheckResponse.getStatus().equals("0")) {

            LOGGER.info("First check response status 0. Everything is okay.");
            IssueEntity issueEntity = IssueEntity
                    .builder()
                    .issuedAmount("0")
                    .paymentStatus(Status.OK.name())
                    .issuedTrCount("0")
                    .build();

            LOGGER.info("information started to save...");
            issueRepo.save(issueEntity);
            LOGGER.info("information saved.");

        } else if (firstCheckResponse.getStatus().equals("1")) {

            LOGGER.warn("First check response status 1. Something went wrong.");
            SecondCheckService secondCheckService = new SecondCheckService(issueRepo);
            secondCheckService.secondCheckRequestMethod(
                    PREMIUM_ENTITY_LIST,
                    SecondCheckRequest
                            .builder()
                            .trType("2")
                            .firstRRN(PREMIUM_ENTITY_LIST.get(0).getRRN())
                            .lastRRN(PREMIUM_ENTITY_LIST.get(PREMIUM_ENTITY_LIST.size() - 1).getRRN())
                            .build());
        } else {
            LOGGER.warn("status doesn't supported");
            throw new UnexpectedValueException("status doesn't supported");
        }
        LOGGER.info("firstCheckResponseMethod() ended.");
    }
}

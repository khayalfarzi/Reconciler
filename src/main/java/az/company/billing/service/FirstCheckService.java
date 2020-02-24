package az.company.billing.service;

import az.company.billing.dto.FirstCheckRequest;
import az.company.billing.dto.FirstCheckResponse;
import az.company.billing.dto.SecondCheckRequest;
import az.company.billing.dto.Status;
import az.company.billing.entities.IssueEntity;
import az.company.billing.entities.PremiumEntity;
import az.company.billing.repositories.IssueRepo;
import az.company.billing.repositories.PremiumRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FirstCheckService {

    private static List<PremiumEntity> PREMIUM_ENTITY_LIST = new ArrayList<>();
    private final PremiumRepo premiumRepo;
    private final IssueRepo issueRepo;

    public FirstCheckService(PremiumRepo premiumRepo, IssueRepo issueRepo) {
        this.premiumRepo = premiumRepo;
        this.issueRepo = issueRepo;
    }

    public void firstCheckRequestMethod() {

        PREMIUM_ENTITY_LIST = premiumRepo.getAllByTransactionDateAndStatus(LocalDate.now(), Status.OK.name());
        FirstCheckRequest firstCheckRequestEntity = new FirstCheckRequest();
        Double totalA = 0.0;

        for (PremiumEntity entity : PREMIUM_ENTITY_LIST) {
            totalA = totalA + entity.getAmount();
        }

        firstCheckRequestEntity.setTrType("0");
        firstCheckRequestEntity.setFirstRRN(PREMIUM_ENTITY_LIST.get(0).getRRN());
        firstCheckRequestEntity.setLastRRN(PREMIUM_ENTITY_LIST.get(PREMIUM_ENTITY_LIST.size() - 1).getRRN());
        firstCheckRequestEntity.setTotalOperCount(String.format("%s", PREMIUM_ENTITY_LIST.size()));
        firstCheckRequestEntity.setTotalAmount(totalA);

        String url = "http://localhost:9090/firstCheckResponseXml";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<FirstCheckRequest> firstCheckRequestHttpEntity = new HttpEntity<>(firstCheckRequestEntity, httpHeaders);

        ResponseEntity<FirstCheckResponse> responseEntity = restTemplate.postForEntity(url, firstCheckRequestHttpEntity, FirstCheckResponse.class);

        System.out.println(firstCheckRequestEntity.toString());

        firstCheckResponseMethod(Objects.requireNonNull(responseEntity.getBody()));

    }

    private void firstCheckResponseMethod(FirstCheckResponse firstCheckResponse) {
        IssueEntity issueEntity = new IssueEntity();

        if (firstCheckResponse.getStatus().equals("0")) {

            issueEntity.setPaymentStatus("OK");
            issueEntity.setIssuedTrCount("0");
            issueRepo.save(issueEntity);


        } else if (firstCheckResponse.getStatus().equals("1")) {
            System.out.println(firstCheckResponse.toString());

            issueEntity.setPaymentStatus(Status.ERROR.name());
            issueEntity.setIssuedTrCount("1");

            SecondCheckService secondCheckService = new SecondCheckService();
            secondCheckService.secondCheckRequestMethod(PREMIUM_ENTITY_LIST, new SecondCheckRequest(
                    "2",
                    PREMIUM_ENTITY_LIST.get(0).getRRN(),
                    PREMIUM_ENTITY_LIST.get(PREMIUM_ENTITY_LIST.size() - 1).getRRN()));

            issueRepo.save(issueEntity);

        }
    }

}

package az.company.billing.service;

import az.company.billing.dto.SecondCheckRequest;
import az.company.billing.dto.SecondCheckResponse;
import az.company.billing.entities.PremiumEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class SecondCheckService {

    void secondCheckRequestMethod(List<PremiumEntity> PREMIUM_ENTITY_LIST, SecondCheckRequest secondCheckRequest) {

        System.out.println(secondCheckRequest.toString());

        String url = "http://localhost:9090/secondCheckResponseXml";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<SecondCheckRequest> secondCheckRequestHttpEntity =
                new HttpEntity<>(secondCheckRequest, httpHeaders);

        ResponseEntity<SecondCheckResponse> secondCheckResponseEntity =
                restTemplate.postForEntity(url, secondCheckRequestHttpEntity, SecondCheckResponse.class);

        System.out.println(secondCheckResponseEntity.getBody());
        secondCheckResponseMethod(PREMIUM_ENTITY_LIST, Objects.requireNonNull(secondCheckResponseEntity.getBody()));

    }

    private void secondCheckResponseMethod(List<PremiumEntity> PREMIUM_ENTITY_LIST, SecondCheckResponse secondCheckResponse) {

        for (int i = 0; i < secondCheckResponse.getPayments().size(); i++) {
            if (!PREMIUM_ENTITY_LIST.get(i).getRRN().equals(secondCheckResponse.getPayments().get(i).getRRN())
                    && !PREMIUM_ENTITY_LIST.get(i).getAmount().equals(secondCheckResponse.getPayments().get(i).getAmount())) {
                log.warn("AzeriCard side: " + PREMIUM_ENTITY_LIST.get(i) + "Bank side: " + secondCheckResponse.getPayments().get(i));
            } else {
                log.info("everything okay !!!");
            }
        }
    }
}

package az.company.billing.process;

import az.company.billing.dto.FirstCheckRequest;
import az.company.billing.dto.FirstCheckResponse;
import az.company.billing.dto.SecondCheckRequest;
import az.company.billing.dto.SecondCheckResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Parser {

    private static final Logger LOGGER = Logger.getLogger(Parser.class);
    private static final HttpHeaders HTTP_HEADERS = new HttpHeaders();
    private static final RestTemplate restTemplate = new RestTemplate();

    public static ResponseEntity<FirstCheckResponse> firstCheckResponseEntity(String url, FirstCheckRequest firstCheckRequest) {

        HTTP_HEADERS.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<FirstCheckRequest> firstCheckRequestHttpEntity = new HttpEntity<>(firstCheckRequest, HTTP_HEADERS);
        return restTemplate.postForEntity(url, firstCheckRequestHttpEntity, FirstCheckResponse.class);
    }

    public static ResponseEntity<SecondCheckResponse> secondCheckResponseEntity(String url, SecondCheckRequest secondCheckRequest) {

        HTTP_HEADERS.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<SecondCheckRequest> secondCheckRequestHttpEntity = new HttpEntity<>(secondCheckRequest, HTTP_HEADERS);
        return restTemplate.postForEntity(url, secondCheckRequestHttpEntity, SecondCheckResponse.class);
    }
}

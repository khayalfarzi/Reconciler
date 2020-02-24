package az.company.billing.controller;

import az.company.billing.entities.PremiumEntity;
import az.company.billing.service.PremiumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;


@Slf4j
@RestController
public class PremiumController {

    private final PremiumService premiumService;

    public PremiumController(PremiumService premiumService) {
        this.premiumService = premiumService;
    }

    @GetMapping("/save")
    public void save() {
        PremiumEntity entity = new PremiumEntity();
        entity.setAmount(567465.67);
        entity.setTransactionDate(LocalDate.now());
        entity.setTransactionTime(LocalTime.now());
        premiumService.save(entity);
    }


//    @PostMapping(value = "/firstCheckResponseXml", consumes = MediaType.APPLICATION_XML_VALUE)
//    public FirstCheckResponse firstCheckResponse(@RequestBody FirstCheckResponse firstCheckResponse) {
//        return premiumService.firstCheckResponse(firstCheckResponse);
//    }


//    @PostMapping(value = "/testXml", consumes = MediaType.APPLICATION_XML_VALUE)
//    public FirstCheckResponse test(@RequestBody FirstCheckRequest firstCheckRequest) {
//        return premiumService.test(firstCheckRequest);
//    }

}

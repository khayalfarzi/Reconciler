package az.company.billing.controller;

import az.company.billing.service.FirstCheckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PremiumController {

    private final FirstCheckService firstCheckService;

    public PremiumController(FirstCheckService firstCheckService) {
        this.firstCheckService = firstCheckService;
    }

    @Scheduled(fixedRate = 20000)
    public void firstCheckRequest() {
        firstCheckService.firstCheckRequestMethod();
    }

}

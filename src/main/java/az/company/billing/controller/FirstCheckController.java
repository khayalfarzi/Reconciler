package az.company.billing.controller;

import az.company.billing.service.FirstCheckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstCheckController {

    private final FirstCheckService firstCheckService;

    public FirstCheckController(FirstCheckService firstCheckService) {
        this.firstCheckService = firstCheckService;
    }

    @Scheduled(fixedRate = 5000)
    public void firstCheckRequest() {
        firstCheckService.firstCheckRequestMethod();
    }

    //reconcile
}

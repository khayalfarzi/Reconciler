package az.company.billing.service;

import az.company.billing.entities.PremiumEntity;
import az.company.billing.repositories.PremiumRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PremiumService {

    private final PremiumRepo premiumRepo;

    public PremiumService(PremiumRepo premiumRepo) {
        this.premiumRepo = premiumRepo;
    }

    public void save(PremiumEntity entity) {
        premiumRepo.save(entity);
    }


    //
//    public FirstCheckResponse test(FirstCheckRequest firstCheckRequest) {
//        System.out.println(firstCheckRequest.toString());
//        return new FirstCheckResponse();
//    }

}

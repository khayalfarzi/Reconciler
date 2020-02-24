package az.company.billing.repositories;

import az.company.billing.entities.PremiumEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PremiumRepo extends CrudRepository<PremiumEntity, Integer> {

    @Query(value = "SELECT * FROM premium_entity WHERE transaction_date=?1 AND status=?2", nativeQuery = true)
    List<PremiumEntity> getAllByTransactionDateAndStatus(LocalDate date, String status);
}

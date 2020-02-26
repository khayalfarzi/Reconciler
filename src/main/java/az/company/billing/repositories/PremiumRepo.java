package az.company.billing.repositories;

import az.company.billing.entities.PremiumEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PremiumRepo extends CrudRepository<PremiumEntity, Integer> {

    //@Query(value = "SELECT * FROM premium_entity WHERE transaction_date=?1 OR transaction_time=BETWEEN (?2 AND ?3) AND status=?4", nativeQuery = true)

    @Query(value = "SELECT * FROM premium_entity WHERE transaction_date=?1  AND status=?2", nativeQuery = true)
    List<PremiumEntity> getAllByTransactionDateAndStatus(LocalDate date, String status);

    @Query(value = "select sum(amount)from premium_entity where transaction_date=?1", nativeQuery = true)
    Double getTotalAmount(LocalDate date);
}

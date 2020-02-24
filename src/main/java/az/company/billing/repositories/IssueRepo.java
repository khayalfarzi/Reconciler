package az.company.billing.repositories;

import az.company.billing.entities.IssueEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepo extends CrudRepository<IssueEntity, Integer> {
}

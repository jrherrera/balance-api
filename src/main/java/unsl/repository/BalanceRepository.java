package unsl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import unsl.entities.Balance;

public interface BalanceRepository extends CrudRepository<Balance, Long> {

	Balance findByUserId(@Param("userId") Long userId);
}

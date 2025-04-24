package Finance.Fast_System.dataRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Fast_System.entities.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long>{

}

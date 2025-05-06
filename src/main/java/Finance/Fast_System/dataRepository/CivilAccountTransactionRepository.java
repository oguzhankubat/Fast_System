package Finance.Fast_System.dataRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Fast_System.entities.CivilAccountTransaction;

public interface CivilAccountTransactionRepository extends JpaRepository<CivilAccountTransaction, Long>{

}

package Finance.Fast_System.dataRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Fast_System.entities.CivilAccount;

public interface CivilAccountRepository extends JpaRepository<CivilAccount, Long>{
	
	Optional<CivilAccount> findByAccountIBAN(String accountIBAN);
}

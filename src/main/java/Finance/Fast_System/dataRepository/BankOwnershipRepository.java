package Finance.Fast_System.dataRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Fast_System.entities.BankOwnership;

public interface BankOwnershipRepository extends JpaRepository<BankOwnership, Long>{
	
	Optional<BankOwnership> findByBankCodeToken(String bankCodeToken);
}

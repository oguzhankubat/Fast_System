package Finance.Fast_System.Rules;

import org.springframework.stereotype.Service;

import Finance.Fast_System.business.requests.CreateCivilAccountRequests;
import Finance.Fast_System.dataRepository.BankOwnershipRepository;
import Finance.Fast_System.entities.BankOwnership;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CheckBankOwnership{
	
	private final BankOwnershipRepository bankOwnershipRepository;
	
	public BankOwnership checkBankOwnership(CreateCivilAccountRequests createCivilAccountRequests) {
	
	BankOwnership bank = bankOwnershipRepository
		    .findByBankCodeToken(createCivilAccountRequests.getBankOwnershipToken())
		    .orElseThrow(() -> new RuntimeException("İşlem reddildi! Doğrulama Yapılamadı."));
	return bank;
	}
}

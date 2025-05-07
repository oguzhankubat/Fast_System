package Finance.Fast_System.business.concretes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Fast_System.Core.ModelMapperServices;
import Finance.Fast_System.Rules.CheckCreateBankOwnership;
import Finance.Fast_System.Utilities.TokenGenerator;
import Finance.Fast_System.business.abstracts.BankOwnershipService;
import Finance.Fast_System.business.requests.CreateBankOwnershipRequest;
import Finance.Fast_System.business.responses.AfterCreateBankOwnershipResponse;
import Finance.Fast_System.dataRepository.BankOwnershipRepository;
import Finance.Fast_System.entities.BankOwnership;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class BankOwnershipManager implements BankOwnershipService{
	private final BankOwnershipRepository bankOwnershipRepository;
	private final ModelMapperServices modelMapperServices;
	private final TokenGenerator tokenGenerator;
	private final CheckCreateBankOwnership checkCreateBankOwnership;
	@Override
	public AfterCreateBankOwnershipResponse createBankOwnership(CreateBankOwnershipRequest createBankOwnershipRequest) {
		
		checkCreateBankOwnership.checkCreateBankOwnership(createBankOwnershipRequest);
		
		String bankToken=tokenGenerator.generateToken();
		BankOwnership bank=modelMapperServices.forRequest()
				.map(createBankOwnershipRequest, BankOwnership.class);
		
		bank.setCreatedTime(LocalDateTime.now());
		bank.setBankCodeToken(bankToken);
		bankOwnershipRepository.save(bank);
		
		AfterCreateBankOwnershipResponse response=new AfterCreateBankOwnershipResponse();
		response.setBankOwnershipToken(bankToken);
		
		return response;
	}

}

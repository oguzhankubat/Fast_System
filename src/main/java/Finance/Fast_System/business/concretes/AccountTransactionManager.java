package Finance.Fast_System.business.concretes;

import org.springframework.stereotype.Service;

import Finance.Fast_System.Core.ModelMapperServices;
import Finance.Fast_System.DTO_pojo.ValidatedAccounts;
import Finance.Fast_System.Rules.CheckAccountTransactionBeforeProcess;
import Finance.Fast_System.Utilities.TransactionNumberGenerator;
import Finance.Fast_System.business.abstracts.AccountTransactionService;
import Finance.Fast_System.business.requests.AccountTransactionToFastSystemRequests;
import Finance.Fast_System.business.responses.AfterAccountTransactionToOwnerAccountResponses;
import Finance.Fast_System.dataRepository.CivilAccountTransactionRepository;
import Finance.Fast_System.entities.CivilAccountTransaction;
import Finance.Fast_System.entities.CivilAccount;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountTransactionManager implements AccountTransactionService{
	private final CivilAccountTransactionRepository accountTransactionRepository;
	private final TransactionNumberGenerator transactionNumberGenerator;
	private final ModelMapperServices modelMapperServices;
	private final CheckAccountTransactionBeforeProcess checkAccountTransactionBeforeProcess;
	
	
	@Override
	@Transactional
	public AfterAccountTransactionToOwnerAccountResponses createTransactionToFastSystem(
	        AccountTransactionToFastSystemRequests request) {

		
	    ValidatedAccounts validatedAccounts = checkAccountTransactionBeforeProcess.validateTransaction(request);
	    CivilAccount ownerAccount = validatedAccounts.getSourceAccount();
	    CivilAccount receiptAccount = validatedAccounts.getReceiptAccount();
		
	    String transactionNumber = transactionNumberGenerator.generateTransactionNumber();


	    double transactionAmount = request.getTransactionAmount();	   
	    
	    CivilAccountTransaction ownerTransaction=modelMapperServices.forRequest()
	    		.map(request, CivilAccountTransaction.class);


	    AfterAccountTransactionToOwnerAccountResponses response = new AfterAccountTransactionToOwnerAccountResponses();
	    response.setTransactionNumber(transactionNumber); 
	    response.setReceiptPersonName(receiptAccount.getCivil().getPersonName());
	    response.setReceiptPersonLastName(receiptAccount.getCivil().getPersonLastName());

	    return response;
	}


}

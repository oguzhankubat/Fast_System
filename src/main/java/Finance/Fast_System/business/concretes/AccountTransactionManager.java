package Finance.Fast_System.business.concretes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Fast_System.Core.ModelMapperServices;
import Finance.Fast_System.DTO_pojo.ValidatedAccounts;
import Finance.Fast_System.Rules.CheckAccountTransactionBeforeProcess;
import Finance.Fast_System.Utilities.TransactionNumberGenerator;
import Finance.Fast_System.business.abstracts.AccountTransactionService;
import Finance.Fast_System.business.requests.AccountTransactionToFastSystemRequests;
import Finance.Fast_System.business.responses.AfterAccountTransactionToOwnerAccountResponses;
import Finance.Fast_System.dataRepository.AccountTransactionRepository;
import Finance.Fast_System.entities.AccountTransaction;
import Finance.Fast_System.entities.CivilAccount;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountTransactionManager implements AccountTransactionService{
	private final AccountTransactionRepository accountTransactionRepository;
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
	    
	    AccountTransaction ownerTransaction=modelMapperServices.forRequest()
	    		.map(request, AccountTransaction.class);
	    
	    ownerTransaction.setTransactionNumber(transactionNumber);
	    ownerTransaction.setTransactionTime(LocalDateTime.now());
	    ownerTransaction.setTransactionAmount(-transactionAmount); 
	    ownerTransaction.setCivilAccount(ownerAccount); 

	    
	    AccountTransaction receiptTransaction=modelMapperServices.forRequest()
	    		.map(request, AccountTransaction.class);
	    
	    receiptTransaction.setTransactionNumber(transactionNumber);
	    receiptTransaction.setTransactionTime(LocalDateTime.now());
	    receiptTransaction.setTransactionAmount(transactionAmount); 
	    receiptTransaction.setCivilAccount(receiptAccount); 
	    
	    //bu araya alıcı bankaya post işlemi eklenecek. başarısız durum olursa veritabanına
	    //kayıt edilmeden @transactional ile işlemler geri sarılacak.
	    
	    accountTransactionRepository.save(ownerTransaction);
	    accountTransactionRepository.save(receiptTransaction);


	    AfterAccountTransactionToOwnerAccountResponses response = new AfterAccountTransactionToOwnerAccountResponses();
	    response.setTransactionNumber(transactionNumber); 
	    response.setReceiptPersonName(receiptAccount.getCivil().getPersonName());
	    response.setReceiptPersonLastName(receiptAccount.getCivil().getPersonLastName());
	    response.setReceiptBankName(receiptAccount.getAccountHolderBank());

	    return response;
	}


}

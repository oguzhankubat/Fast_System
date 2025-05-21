package Finance.Fast_System.business.concretes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Fast_System.BackgroundProcesses.BackgroundRedirectAccountTransactionProcess;
import Finance.Fast_System.Core.ModelMapperServices;
import Finance.Fast_System.DTO_pojo.ValidatedAccounts;
import Finance.Fast_System.Enums.TransactionDirection;
import Finance.Fast_System.Rules.CheckAccountTransactionBeforeProcess;
import Finance.Fast_System.Utilities.TransactionNumberGenerator;
import Finance.Fast_System.business.abstracts.AccountTransactionService;
import Finance.Fast_System.business.requests.AccountTransactionToFastSystemRequests;
import Finance.Fast_System.business.responses.AfterAccountTransactionToOwnerAccountResponses;
import Finance.Fast_System.dataRepository.CivilAccountTransactionRepository;
import Finance.Fast_System.dataRepository.TransactionDetailRepository;
import Finance.Fast_System.entities.CivilAccount;
import Finance.Fast_System.entities.CivilAccountTransaction;
import Finance.Fast_System.entities.TransactionDetail;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountTransactionManager implements AccountTransactionService{
	private final CivilAccountTransactionRepository civilAccountTransactionRepository;
	private final TransactionNumberGenerator transactionNumberGenerator;
	private final ModelMapperServices modelMapperServices;
	private final CheckAccountTransactionBeforeProcess checkAccountTransactionBeforeProcess;
	private final TransactionDetailRepository transactionDetailRepository;
	private final BackgroundRedirectAccountTransactionProcess backgroundRedirectAccountTransactionProcess;
	
	@Override
	@Transactional
	public AfterAccountTransactionToOwnerAccountResponses createTransactionToFastSystem(
	        AccountTransactionToFastSystemRequests request) {

		
	    ValidatedAccounts validatedAccounts = checkAccountTransactionBeforeProcess.validateTransaction(request);
	    CivilAccount ownerAccount = validatedAccounts.getSourceAccount();
	    CivilAccount receiptAccount = validatedAccounts.getReceiptAccount();
		
	    String transactionNumber = transactionNumberGenerator.generateTransactionNumber();
   
	    
	    TransactionDetail transactionDetail=modelMapperServices.forRequest()
	    		.map(request, TransactionDetail.class);
	    transactionDetail.setTransactionNumber(transactionNumber);
	    transactionDetail.setSenderBankAccountIban(request.getAccountIBAN());
	    transactionDetail.setTransactionTime(LocalDateTime.now());

	    CivilAccountTransaction ownerTransaction= new CivilAccountTransaction();
	    ownerTransaction.setCivilAccount(ownerAccount);
	    ownerTransaction.setTransactionDetail(transactionDetail);
	    ownerTransaction.setDirection(TransactionDirection.OUTGOING);
    
	    CivilAccountTransaction receiptTransaction=new CivilAccountTransaction();
	    receiptTransaction.setCivilAccount(receiptAccount);
	    receiptTransaction.setDirection(TransactionDirection.INCOMING);
	    receiptTransaction.setTransactionDetail(transactionDetail);
	    
	    backgroundRedirectAccountTransactionProcess.redirectTransaction(request, transactionNumber);
	    
	    transactionDetailRepository.save(transactionDetail);
	    civilAccountTransactionRepository.save(ownerTransaction);
	    civilAccountTransactionRepository.save(receiptTransaction);
	    
	    
	    
	    AfterAccountTransactionToOwnerAccountResponses response = new AfterAccountTransactionToOwnerAccountResponses();
	    response.setTransactionNumber(transactionNumber); 
	    response.setReceiptPersonName(receiptAccount.getCivil().getPersonName());
	    response.setReceiptPersonLastName(receiptAccount.getCivil().getPersonLastName());
	    response.setReceiptBankName(receiptAccount.getAccountOwnerBank().getAccountOwnershipBank());

	    return response;
	}


}

package Finance.Fast_System.business.concretes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import Finance.Fast_System.Core.ModelMapperServices;
import Finance.Fast_System.Rules.CheckBankOwnership;
import Finance.Fast_System.Rules.CheckCurrency;
import Finance.Fast_System.Rules.CheckDisableAndEnableAccountRules;
import Finance.Fast_System.Rules.CheckTcKimlikNumberRule;
import Finance.Fast_System.Utilities.IbanGenerator;
import Finance.Fast_System.Utilities.TokenGenerator;
import Finance.Fast_System.business.abstracts.CivilAccountService;
import Finance.Fast_System.business.requests.CreateCivilAccountRequests;
import Finance.Fast_System.business.requests.EnableAndDisableCivilAccountRequests;
import Finance.Fast_System.business.responses.AfterCreateCivilAccountResponses;
import Finance.Fast_System.dataRepository.CivilAccountRepository;
import Finance.Fast_System.entities.BankOwnership;
import Finance.Fast_System.entities.Civil;
import Finance.Fast_System.entities.CivilAccount;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CivilAccountManager implements CivilAccountService{
	
	private final CivilAccountRepository civilAccountRepository;
	private final ModelMapperServices modelMapperServices;
	private final IbanGenerator ibanGenerator;
	private final TokenGenerator tokenGenerator;
	private final CheckTcKimlikNumberRule checkTcKimlikNumberRule;
	private final CheckDisableAndEnableAccountRules checkDisableAndEnableAccountRules;
	private final CheckCurrency checkCurrency;
	private final CheckBankOwnership checkBankOwnership;
	
	@Override
	public AfterCreateCivilAccountResponses createCivilAccount(
			CreateCivilAccountRequests createCivilAccountRequests ) {
		Civil civil=checkTcKimlikNumberRule.checkTcKimlikNumber(createCivilAccountRequests);
		
		checkCurrency.validate(createCivilAccountRequests.getAccountCurrency());  
        
		BankOwnership bank=checkBankOwnership.checkBankOwnership(createCivilAccountRequests);
		
		
        CivilAccount civilAccount=modelMapperServices.forRequest()
        		.map(createCivilAccountRequests, CivilAccount.class);
        	
        
        String token = tokenGenerator.generateToken();
        String iban = ibanGenerator.generateIban(
                bank.getBankCode(),
                createCivilAccountRequests.getBranchCode(),
                createCivilAccountRequests.getAccountNumber());
        
        civilAccount.setAccountIBAN(iban);
        civilAccount.setAccountToken(token);
        civilAccount.setAccountStatus("Pending Verification");
        civilAccount.setCivil(civil);
        civilAccount.setCreatedTime(LocalDateTime.now());
        civilAccount.setAccountOwnerBank(bank);
        
		civilAccountRepository.save(civilAccount);
		
		AfterCreateCivilAccountResponses afterCreateCivilAccountResponses = new AfterCreateCivilAccountResponses();
		afterCreateCivilAccountResponses.setAccountToken(token);
		afterCreateCivilAccountResponses.setAccountIBAN(iban);

		
		return afterCreateCivilAccountResponses;
		
		
	}

	@Override
	public String disableCivilAccount(EnableAndDisableCivilAccountRequests enableAndDisableCivilAccountRequests) {
		
		CivilAccount account=checkDisableAndEnableAccountRules.validateAccountOwnership(enableAndDisableCivilAccountRequests);


        account.setAccountStatus("Passive");
        civilAccountRepository.save(account);
        
        return "Hesap işleme kapatıldı!";
	}

	@Override
	public String enableCivilAccount(EnableAndDisableCivilAccountRequests enableAndDisableCivilAccountRequests) {
		CivilAccount account=checkDisableAndEnableAccountRules.validateAccountOwnership(enableAndDisableCivilAccountRequests);
		account.setAccountStatus("Active");
		civilAccountRepository.save(account);
		return "Hesap işleme açıldı!";
	}

}

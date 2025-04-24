package Finance.Fast_System.Rules;

import org.springframework.stereotype.Service;

import Finance.Fast_System.business.requests.EnableAndDisableCivilAccountRequests;
import Finance.Fast_System.dataRepository.CivilAccountRepository;
import Finance.Fast_System.entities.CivilAccount;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckDisableAndEnableAccountRules {
    
    private final CivilAccountRepository civilAccountRepository;

    public CivilAccount validateAccountOwnership(EnableAndDisableCivilAccountRequests disableCivilAccountRequests) {

        CivilAccount account = civilAccountRepository.findByAccountIBAN(disableCivilAccountRequests.getAccountIBAN())
                .orElseThrow(() -> new RuntimeException("hesap bulunamadı."));
        

        if (!account.getAccountToken().equals(disableCivilAccountRequests.getAccountToken())) {
            throw new RuntimeException("IBAN ile Token eşleşmiyor. İşlem Yapılamaz!");
        }
        return account;
    }
    
}

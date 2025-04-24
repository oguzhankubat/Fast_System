package Finance.Fast_System.Rules;

import org.springframework.stereotype.Service;

import Finance.Fast_System.DTO_pojo.ValidatedAccounts;
import Finance.Fast_System.business.requests.AccountTransactionToFastSystemRequests;
import Finance.Fast_System.dataRepository.CivilAccountRepository;
import Finance.Fast_System.entities.CivilAccount;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckAccountTransactionBeforeProcess {
    
    private final CivilAccountRepository civilAccountRepository;

    public ValidatedAccounts validateTransaction(AccountTransactionToFastSystemRequests request) {

        CivilAccount sourceAccount = civilAccountRepository.findByAccountIBAN(request.getAccountIBAN())
                .orElseThrow(() -> new RuntimeException("Gönderici hesap bulunamadı."));

        CivilAccount receiptAccount = civilAccountRepository.findByAccountIBAN(request.getReceiptBankAccountIBAN())
                .orElseThrow(() -> new RuntimeException("Alıcı hesap bulunamadı."));

        if (!sourceAccount.getAccountToken().equals(request.getAccountToken())) {
            throw new RuntimeException("IBAN ile Token eşleşmiyor. Aktarım işlemi reddedildi!");
        }
        
        if (!"active".equalsIgnoreCase(sourceAccount.getAccountStatus())) {
            throw new RuntimeException("Gönderici hesap aktif değil. İşlem yapılamaz!");
        }
        
        if (!"active".equalsIgnoreCase(receiptAccount.getAccountStatus())) {
            throw new RuntimeException("Alıcı hesap aktif değil. İşlem yapılamaz!");
        }

        if (!sourceAccount.getAccountCurrency().equals(receiptAccount.getAccountCurrency())) {
            throw new RuntimeException("Hesap türleri eşleşmiyor. Aktarım yapılamaz!");
        }

        return new ValidatedAccounts(sourceAccount, receiptAccount);
    }
}

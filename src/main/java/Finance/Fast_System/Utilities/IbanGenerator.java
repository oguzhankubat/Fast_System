package Finance.Fast_System.Utilities;

import java.util.Random;

import org.springframework.stereotype.Component;

import Finance.Fast_System.dataRepository.CivilAccountRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IbanGenerator {
	private final Random random = new Random();
	private final CivilAccountRepository civilAccountRepository ;
	
	public String generateIban(String bankCode, String branchCode, String accountNumber) {
        if (bankCode.length() != 5 || branchCode.length() != 4 || accountNumber.length() != 7) {
            throw new IllegalArgumentException("Banka kodu 5, şube kodu 4, hesap numarası 7 karakter olmalıdır.");
        }

        String countryCode = "TR";
        String reserveDigit = "0";
        String iban;

        do {
            String controlDigits = String.format("%02d", random.nextInt(100));
            String random99 = String.format("%02d", random.nextInt(100));  
            String random000 = String.format("%03d", random.nextInt(1000));
            String fullAccountNumber = branchCode + accountNumber + random99 + random000;
            iban = countryCode + controlDigits + bankCode + reserveDigit + fullAccountNumber;
        } while (civilAccountRepository.existsByAccountIBAN(iban)); 
        return iban;
    }

}

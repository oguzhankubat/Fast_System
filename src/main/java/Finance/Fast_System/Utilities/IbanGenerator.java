package Finance.Fast_System.Utilities;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class IbanGenerator {
	private final Random random = new Random();
	
    public String generateIban(String bankCode, String branchCode, String accountNumber) {
        if (bankCode.length() != 5 || branchCode.length() != 4 || accountNumber.length() != 7) {
            throw new IllegalArgumentException("Banka kodu 5, şube kodu 4, hesap numarası 7 karakter olmalıdır.");
        }

        String countryCode = "TR";
        
        String controlDigits = String.format("%02d", new Random().nextInt(100));

        String reserveDigit = "0";
        
        String random99 = String.format("%02d", random.nextInt(100));  
        String random000 = String.format("%03d", random.nextInt(1000));

        String fullAccountNumber = branchCode + accountNumber + random99 + random000;

        return countryCode + controlDigits + bankCode + reserveDigit + fullAccountNumber;
    }

}

package Finance.Fast_System.Rules;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckCurrency {

    private final Set<String> acceptedCurrencies = new HashSet<>(Arrays.asList("USD", "EUR", "TRY", "GBP", "CAD", "AUD", "CHF", "JPY", "INR", "NZD", "XAU"));

    public void validate(String accountCurrency) {
        if (accountCurrency == null || accountCurrency.isBlank()) {
            throw new IllegalArgumentException("Para birimi boş olamaz.");
        }

        String upperCaseCurrency = accountCurrency.trim().toUpperCase();

        if (!acceptedCurrencies.contains(upperCaseCurrency)) {
            throw new IllegalArgumentException("Geçersiz para birimi: " + accountCurrency);
        }
    }
}
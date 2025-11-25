package Finance.Fast_System.BackgroundProcesses;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import Finance.Fast_System.business.requests.AccountTransactionToFastSystemRequests;
import Finance.Fast_System.dataRepository.CivilAccountRepository;
import Finance.Fast_System.entities.CivilAccount;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BackgroundRedirectAccountTransactionProcess {

    private final CivilAccountRepository civilAccountRepository;
    private final Environment environment; 

    public void redirectTransaction(AccountTransactionToFastSystemRequests request, String transactionNumber) {
        
        String finalUrl = "Belirlenemedi"; 

        try {
            CivilAccount senderAccount = civilAccountRepository.getByAccountIBAN(request.getAccountIBAN());
            CivilAccount receiverAccount = civilAccountRepository.getByAccountIBAN(request.getReceiptBankAccountIBAN());
            
            // 1. Banka Adını Çek ve Temizle (Boşluk temizliği hayat kurtarır)
            String rawBankName = receiverAccount.getAccountOwnerBank().getAccountOwnershipBank();
            String bankName = rawBankName != null ? rawBankName.trim() : "";
            
            String dbCallbackUrl = receiverAccount.getAccountOwnerBank().getCallbackTransactionUrl();
            
            // 2. Properties Anahtarını Oluştur
            String propertyKey = "bank.host." + bankName;
            String dockerBaseUrl = environment.getProperty(propertyKey);
 
            // ---------------------------------------------
            
            if (dockerBaseUrl != null && !dockerBaseUrl.isEmpty()) {
                finalUrl = dockerBaseUrl + "/api/accountTransaction/incoming";
            } else {
                finalUrl = dbCallbackUrl;
            }

            URI uri = URI.create(finalUrl); 

            String requestBody = String.format(
                "{\"receiptBankAccountIBAN\":\"%s\","
                + "\"transactionAmount\":\"%s\","
                + "\"transactionDescription\":\"%s\","
                + "\"senderPersonName\":\"%s\","
                + "\"senderPersonLastName\":\"%s\","
                + "\"transactionNumber\":\"%s\","
                + "\"senderAccountIBAN\":\"%s\","
                + "\"receiptBankAccountToken\":\"%s\","
                + "\"transactionType\":\"%s\"}",
                request.getReceiptBankAccountIBAN(),
                request.getTransactionAmount(),
                request.getTransactionDescription(),
                senderAccount.getCivil().getPersonName(),      
                senderAccount.getCivil().getPersonLastName(), 
                transactionNumber,
                senderAccount.getAccountIBAN(),              
                receiverAccount.getAccountToken(),
                request.getTransactionType()
            );

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() != 200) {
                throw new RuntimeException("İstek Başarısız! Status Code: " + response.statusCode() + " Body: " + response.body());
            }
            String responseBody = response.body();
            if (!responseBody.contains("success")) {
                throw new RuntimeException("İşlem başarısız! Gelen cevap: " + responseBody);
            }

        } catch (IOException | InterruptedException e) {
            // Hatanın kök nedenini (stack trace) kaybetmemek için 'e' yi zincirliyoruz.
            throw new RuntimeException("Fast System Yönlendirme Hatası! Hedef: " + finalUrl, e);
        }
    }
}
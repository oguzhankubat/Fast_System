package Finance.Fast_System.BackgroundProcesses;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import Finance.Fast_System.business.requests.AccountTransactionToFastSystemRequests;
import Finance.Fast_System.dataRepository.CivilAccountRepository;
import Finance.Fast_System.entities.CivilAccount;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BackgroundRedirectAccountTransactionProcess {

   private final CivilAccountRepository civilAccountRepository;

    public void redirectTransaction(AccountTransactionToFastSystemRequests request, String transactionNumber) {
    	
        try {
        	
            CivilAccount account =civilAccountRepository.getByAccountIBAN(request.getAccountIBAN());
            String bankAccountUrl=account.getAccountOwnerBank().getCallbackTransactionUrl();
            
            CivilAccount receiverAccount = civilAccountRepository.getByAccountIBAN(request.getReceiptBankAccountIBAN());
            
            URI uri = URI.create(bankAccountUrl); 

          
            String requestBody = String.format(
            	    "{\"receiptBankAccountIBAN\":\"%s\","
            	    + "\"transactionAmount\":\"%s\","
            	    + "\"transactionDescription\":\"%s\","
            	    + "\"senderPersonName\":\"%s\","
            	    + "\"senderPersonLastName\":\"%s\","
            	    + "\"transactionNumber\":\"%s\","
            	    + "\"senderAccountIBAN\":\"%s\","
            	    + "\"receiptBankAccountToken\":\"%s\"}",
            	    request.getReceiptBankAccountIBAN(),
            	    request.getTransactionAmount(),
            	    request.getTransactionDescription(),
            	    account.getCivil().getPersonName(),
            	    account.getCivil().getPersonLastName(),
            	    transactionNumber,
            	    account.getAccountIBAN(),
            	    receiverAccount.getAccountToken()
            	);

            
            HttpClient client = HttpClient.newHttpClient();

       
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

       
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() != 200) {
                throw new RuntimeException("İstek Başarısız! Status Code: " + response.statusCode());
            }
            String responseBody = response.body();
            if (!responseBody.contains("success")) {
                throw new RuntimeException("İşlem başarısız! Gelen cevap: " + responseBody);
            }


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Backgorund Redirect Account Transaction İstek gönderilemedi!", e);
        }
    }
}

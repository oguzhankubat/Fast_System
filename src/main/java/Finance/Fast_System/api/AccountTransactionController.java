package Finance.Fast_System.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Finance.Fast_System.business.abstracts.AccountTransactionService;
import Finance.Fast_System.business.requests.AccountTransactionToFastSystemRequests;
import Finance.Fast_System.business.responses.AfterAccountTransactionToOwnerAccountResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transaction")
@AllArgsConstructor
public class AccountTransactionController {
	
	private final AccountTransactionService accountTransactionService;
	
    @PostMapping("/process")
    public AfterAccountTransactionToOwnerAccountResponses createTransactionToFastSystem(@Valid @RequestBody()  AccountTransactionToFastSystemRequests accountTransactionToFastSystemRequests) {
        return accountTransactionService.createTransactionToFastSystem(accountTransactionToFastSystemRequests);
    }
}

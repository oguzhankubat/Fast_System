package Finance.Fast_System.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Finance.Fast_System.business.abstracts.BankOwnershipService;
import Finance.Fast_System.business.requests.CreateBankOwnershipRequest;
import Finance.Fast_System.business.responses.AfterCreateBankOwnershipResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/bankOwnership")
@AllArgsConstructor
public class BankOwnershipController {
	
	public final BankOwnershipService bankOwnershipService;
	
	@PostMapping("/createBankOwnership")
	public AfterCreateBankOwnershipResponse createBankOwnership(@Valid @RequestBody() CreateBankOwnershipRequest createBankOwnershipRequest) {
		return bankOwnershipService.createBankOwnership(createBankOwnershipRequest);
	}
	
	
	
}

package Finance.Fast_System.business.abstracts;

import Finance.Fast_System.business.requests.CreateBankOwnershipRequest;
import Finance.Fast_System.business.responses.AfterCreateBankOwnershipResponse;

public interface BankOwnershipService {
	AfterCreateBankOwnershipResponse createBankOwnership(CreateBankOwnershipRequest createBankOwnershipRequest);
}

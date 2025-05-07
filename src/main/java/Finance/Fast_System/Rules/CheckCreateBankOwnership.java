package Finance.Fast_System.Rules;

import org.springframework.stereotype.Service;

import Finance.Fast_System.business.requests.CreateBankOwnershipRequest;
import Finance.Fast_System.dataRepository.BankOwnershipRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckCreateBankOwnership {
    private final BankOwnershipRepository bankOwnershipRepository;

    public void checkCreateBankOwnership(CreateBankOwnershipRequest createBankOwnershipRequest) {

        boolean exists = bankOwnershipRepository.existsByownershipbankVknNumber(createBankOwnershipRequest.getOwnershipbankVknNumber());

        if (exists) {
            throw new RuntimeException("İşlem reddildi!");
        }
    }
}

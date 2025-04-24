package Finance.Fast_System.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterAccountTransactionToOwnerAccountResponses {
	
	private String transactionNumber;
	
    private String receiptPersonName;

    private String receiptPersonLastName;
    
    private String receiptBankName;
	
}

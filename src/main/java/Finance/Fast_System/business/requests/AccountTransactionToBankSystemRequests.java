package Finance.Fast_System.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionToBankSystemRequests {
	@NotNull
	@NotBlank
	@Size(min=26,max = 26)
	private String accountIBAN;
	
	@NotNull
	@NotBlank
	private double transactionAmount;
	
	@NotNull
	@NotBlank
	private String transactionDescription;
	
	
	@NotNull
	@NotBlank
	private String receiptBankAccountIBAN;
	
	@NotNull
	@NotBlank
	private String personName;
	
	@NotNull
	@NotBlank
	private String personLastName;

}

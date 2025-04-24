package Finance.Fast_System.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountTransactionToFastSystemRequests {
	
	@NotNull
	@NotBlank
	@Size(min=26,max = 26)
	private String accountIBAN;
	
	@NotNull
	@NotBlank
	@Size(min=36,max = 36)
	private String accountToken;
	
	@NotNull
	@Positive(message = "Sadece pozitif sayı girilebilir!")
	private Double transactionAmount;
	
	@NotNull
	@NotBlank
	@Size(max = 60)
	private String transactionDescription;
	
	@NotNull
	@NotBlank
	@Size(min=26,max = 26)
	private String receiptBankAccountIBAN;
	
}

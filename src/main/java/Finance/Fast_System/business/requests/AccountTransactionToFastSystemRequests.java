package Finance.Fast_System.business.requests;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import Finance.Fast_System.Core.UpperCaseDeserializer;
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
	private BigDecimal transactionAmount;
	
	@NotNull
	@NotBlank
	private String transactionType;
	
	@NotNull
	@NotBlank
	@Size(max = 150)
	@JsonDeserialize(using = UpperCaseDeserializer.class)
	private String transactionDescription;
	
	@NotNull
	@NotBlank
	@Size(min=26,max = 26)
	private String receiptBankAccountIBAN;
	
}

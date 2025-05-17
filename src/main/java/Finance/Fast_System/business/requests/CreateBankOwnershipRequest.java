package Finance.Fast_System.business.requests;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import Finance.Fast_System.Core.UpperCaseDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankOwnershipRequest {
	@NotNull
	@NotBlank
	@Size(min = 10,max = 10)
	private String ownershipbankVknNumber;
	
	@NotNull
	@NotBlank
	@JsonDeserialize(using = UpperCaseDeserializer.class)
	private String accountOwnershipBank;

	@NotNull
	@NotBlank
	@Size(min=5,max = 5)
	private String bankCode;
	
	@NotNull
	@NotBlank
	@URL
	private String callbackTransactionUrl;
	
	
	
}

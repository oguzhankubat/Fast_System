package Finance.Fast_System.business.requests;

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
	private String accountOwnershipBank;

	@NotNull
	@NotBlank
	@Size(min=5,max = 5)
	private String bankCode;
	
	
	
}

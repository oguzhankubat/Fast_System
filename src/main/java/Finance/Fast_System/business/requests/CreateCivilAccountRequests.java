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
public class CreateCivilAccountRequests {
	
	@NotNull
	@NotBlank
	@Size(min = 11,max = 11)
	private String tcKimlikNumber;

	
	@NotNull
	@NotBlank
	private String accountType;
	
	@NotNull
	@NotBlank
	@Size(min=3,max = 3)
    private String accountCurrency;
	
	
	@NotNull
	@NotBlank
	@Size(min=4,max = 4)
	private String branchCode;
	
	@NotNull
	@NotBlank
	@Size(min = 7,max = 7)
	private String accountNumber;
	
	@NotNull
	@NotBlank
	@Size(min=36,max = 36)
	private String bankOwnershipToken;
	
}

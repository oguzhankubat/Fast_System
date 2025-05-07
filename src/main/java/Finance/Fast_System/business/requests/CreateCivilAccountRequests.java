package Finance.Fast_System.business.requests;

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
public class CreateCivilAccountRequests {
	
	@NotNull
	@NotBlank
	@Size(min = 11,max = 11)
	private String tcKimlikNumber;

	
	@NotNull
	@NotBlank
	@Size(max = 20)
	@JsonDeserialize(using = UpperCaseDeserializer.class)
	private String accountType;
	
	@NotNull
	@NotBlank
	@Size(min=3,max = 3)
	@JsonDeserialize(using = UpperCaseDeserializer.class)
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

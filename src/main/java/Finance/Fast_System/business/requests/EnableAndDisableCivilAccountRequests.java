package Finance.Fast_System.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EnableAndDisableCivilAccountRequests {
	@NotNull
	@NotBlank
	@Size(min = 26,max = 26)
	private String accountIBAN;
	
	@NotNull
	@NotBlank
	@Size(min=36,max=36)
	private String accountToken;
}

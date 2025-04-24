package Finance.Fast_System.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AfterCreateCivilAccountResponses {
	
	private String accountToken;

	private String accountIBAN;
	
}

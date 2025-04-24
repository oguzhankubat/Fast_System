package Finance.Fast_System.business.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class CivilSystemAPIResponse {
	
    private String personName;

    private String personLastName;

    private String tcKimlikNumber;

    private String gender;
    
    private LocalDate birthDate;

}

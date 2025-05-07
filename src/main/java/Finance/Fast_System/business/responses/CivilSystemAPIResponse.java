package Finance.Fast_System.business.responses;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import Finance.Fast_System.Core.UpperCaseDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class CivilSystemAPIResponse {
	
	@JsonDeserialize(using = UpperCaseDeserializer.class)
    private String personName;

	@JsonDeserialize(using = UpperCaseDeserializer.class)
    private String personLastName;

    private String tcKimlikNumber;

    @JsonDeserialize(using = UpperCaseDeserializer.class)
    private String gender;
    
    private LocalDate birthDate;

}

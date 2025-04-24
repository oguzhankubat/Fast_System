package Finance.Fast_System.DTO_pojo;

import Finance.Fast_System.entities.CivilAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidatedAccounts {
    private final CivilAccount sourceAccount;
    private final CivilAccount receiptAccount;
}
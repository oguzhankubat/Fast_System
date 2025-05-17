package Finance.Fast_System.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bank_ownership")
public class BankOwnership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_ownership_bank", nullable = false,length = 15,unique = true)
    private String accountOwnershipBank;

    @Column(name = "ownership_bank_vkn_number", nullable = false,length = 10,unique = true)
    private String ownershipbankVknNumber;
    
    @Column(name = "bank_code",unique = true,nullable = false,length = 5)
    private String bankCode;
    
    @Column(name = "bank_code_token",unique = true,nullable = false,length = 36)
    private String bankCodeToken;
    
    @Column(name="callback_transaction_url")
    private String callbackTransactionUrl;
    
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    
    @OneToMany(mappedBy = "accountOwnerBank")
    private List<CivilAccount> civilAccounts;
    

}


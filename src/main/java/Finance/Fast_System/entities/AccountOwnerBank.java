package Finance.Fast_System.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account_owner_bank", indexes = {
        @Index(name = "idx_holderbank_vkn_number", columnList = "holderbank_vkn_number")
})
public class AccountOwnerBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "account_holder_bank", nullable = false,length = 15,unique = true)
    private String accountHolderBank;

    @Column(name = "holderbank_vkn_number", nullable = false,length = 10,unique = true)
    private String holderbankVknNumber;
    
    @Column(name = "bank_code",unique = true,nullable = false)
    private String bankCode;
    
    @Column(name = "bank_code_token",unique = true,nullable = false)
    private String bankCodeToken;
    
    @OneToMany(mappedBy = "accountOwnerBank")
    private List<CivilAccount> civilAccounts;
    

}


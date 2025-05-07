package Finance.Fast_System.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "civil_accounts", indexes = {
        @Index(name = "idx_civil_account_tc_kimlik_number", columnList = "civil_account_tc_kimlik_number")
})
public class CivilAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "account_IBAN",unique = true,length = 26)
    private String accountIBAN;
    
    @Column(name = "account_type", nullable = false,length = 20)
    private String accountType;
    
    @Column(name = "account_currency", nullable = false,length = 5)
    private String accountCurrency;
    
    @Column(name = "account_token", unique = true,length = 36) 
    private String accountToken;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "account_status", nullable = false,length = 7)
    private String accountStatus;
    
    
    @Column(name = "branch_code",length = 5)
    private String branchCode;
    
    @Column(name="account_number",length = 7)
    private String accountNumber;
    
    @ManyToOne
    @JoinColumn(name = "civil_account_tc_kimlik_number", referencedColumnName = "tc_kimlik_number", updatable = false)
    private Civil civil;
    
    @ManyToOne
    @JoinColumn(name="account_owner_bank")
    private BankOwnership accountOwnerBank;  

    @OneToMany(mappedBy = "civilAccount")
    private List<CivilAccountTransaction> accountTransactions;
}

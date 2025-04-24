package Finance.Fast_System.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account_transactions", indexes = {
        @Index(name = "idx_account_transaction_civil_account_id", columnList = "civil_account_id")
})
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "transaction_number",length = 30)
    private String transactionNumber;
    
    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Column(name = "transaction_amount")
    private double transactionAmount;
    
    @Column(name="transaction_description",length = 60)
    private String transactionDescription;
    
    @Column(name="sender_bank_account_ıban")
    private String accountIBAN;
    
    @Column(name="receipt_bank_account_ıban")
    private String receiptBankAccountIBAN;

    
    @ManyToOne
    @JoinColumn(name = "civil_account_id", updatable = false)
    private CivilAccount civilAccount;
}

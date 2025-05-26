package Finance.Fast_System.entities;

import java.math.BigDecimal;
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



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_details")
public class TransactionDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transaction_number", length = 30,unique = true)
    private String transactionNumber;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Column(name = "transaction_amount",precision = 18,scale = 2)
    private BigDecimal transactionAmount;

    @Column(name = "transaction_description",length = 150)
    private String transactionDescription;

    @Column(name = "sender_bank_account_iban",length = 26)
    private String senderBankAccountIban;
    
    @Column(name = "transaction_type",length = 20)
    private String transactionType;
    
    @Column(name = "receipt_bank_account_iban",length = 26)
    private String receiptBankAccountIban;

    @OneToMany(mappedBy = "transactionDetail")
    private List<CivilAccountTransaction> accountTransactions;
}

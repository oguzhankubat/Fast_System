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



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_details", indexes = {
	    @Index(name = "idx_transaction_number", columnList = "transaction_number")
	})
public class TransactionDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transaction_number", length = 30,unique = true)
    private String transactionNumber;

    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "transaction_description", length = 60)
    private String transactionDescription;

    @Column(name = "sender_bank_account_iban")
    private String senderBankAccountIban;

    @Column(name = "receipt_bank_account_iban")
    private String receiptBankAccountIban;

    @OneToMany(mappedBy = "transactionDetail")
    private List<CivilAccountTransaction> accountTransactions;
}

package Finance.Fast_System.entities;

import Finance.Fast_System.Enums.TransactionDirection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "civil_account_transactions")
public class CivilAccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transaction_detail_id")
    private TransactionDetail transactionDetail;

    @ManyToOne
    @JoinColumn(name = "civil_account_id", updatable = false)
    private CivilAccount civilAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_direction", nullable = false,length = 8)
    private TransactionDirection direction;
}
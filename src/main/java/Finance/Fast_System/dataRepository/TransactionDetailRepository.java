package Finance.Fast_System.dataRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Fast_System.entities.TransactionDetail;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long>{

}

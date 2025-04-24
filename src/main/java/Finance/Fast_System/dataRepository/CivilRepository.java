package Finance.Fast_System.dataRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Finance.Fast_System.entities.Civil;

public interface CivilRepository extends JpaRepository<Civil, Long>{
	
	Optional<Civil> findByTcKimlikNumber(String tcKimlikNumber);
}

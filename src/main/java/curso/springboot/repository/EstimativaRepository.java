package curso.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.springboot.model.Estimativa;

@Repository
@Transactional
public interface EstimativaRepository extends CrudRepository<Estimativa, Long>{

	@Query("select e from Estimativa e where e.item.id = ?1")
	public List<Estimativa> getEstimativas(Long itemid);
}

package curso.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.springboot.model.Item;

@Repository
@Transactional
public interface ItemRepository extends CrudRepository<Item, Long>{

	@Query("select i from Item i where i.nome like %?1%")
	List<Item> buscarItemPorNome(String nome);
}

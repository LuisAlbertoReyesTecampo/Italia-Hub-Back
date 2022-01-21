package italia.hub.api.repository;

import italia.hub.api.exception.BusinessException;
import italia.hub.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PruebaRepository extends JpaRepository<Cliente, Integer> {

	@Query("select c from Cliente c where c.activo = true")
	List<Cliente> getClientes() throws BusinessException;

	@Modifying
	@Query("update Cliente c set c.activo = 0 where c.id = :id")
	int bajaLogica(@Param("id") int id) throws BusinessException;

}

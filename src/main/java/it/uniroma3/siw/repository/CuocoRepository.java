package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Cuoco;

public interface CuocoRepository extends CrudRepository<Cuoco, Long> {
	Cuoco findByCredenzialiUsername(String username);
	Optional<Cuoco> findById(Long id);
	@Query("SELECT c FROM Cuoco c LEFT JOIN FETCH c.ricette WHERE c.id = :id")
	Cuoco findByIdWithRicette(Long id);
	@Query("SELECT c FROM Cuoco c LEFT JOIN FETCH c.ricette WHERE c.credenziali.username = :username")
    Cuoco findByUsernameWithRicette(@Param("username") String username);
}

package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Utente;

public interface CuocoRepository extends CrudRepository<Cuoco, Long> {
	Cuoco findByUtente(Utente utente);	
}

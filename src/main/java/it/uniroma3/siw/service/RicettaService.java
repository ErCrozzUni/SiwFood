package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;

@Service
public class RicettaService {
	@Autowired
	private RicettaRepository ricettaRepository;

	public Ricetta findById(Long id) {
		return ricettaRepository.findById(id).get();
	}

	public Iterable<Ricetta> findAll() {
		return ricettaRepository.findAll();
	}
	
	public List<Ricetta> findRicetteByCuoco(Cuoco cuoco) {
        return ricettaRepository.findByCuoco(cuoco);
    }
	
	public Ricetta saveRicetta(Ricetta ricetta) {
		return ricettaRepository.save(ricetta);
	}
	
	public Optional<Ricetta> getRicetta(Long id) {
		return ricettaRepository.findById(id);
	}

	public Iterable<Ricetta> getAllRicette() {
		return ricettaRepository.findAll();
	}

	public void deleteRicetta(Long id) {
		ricettaRepository.deleteById(id);
	}
}

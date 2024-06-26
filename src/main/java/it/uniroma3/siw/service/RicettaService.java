package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;

@Service
public class RicettaService {
    
    @Autowired
    private RicettaRepository ricettaRepository;

    @Transactional
    public List<Ricetta> findRicetteByCuoco(Cuoco cuoco) {
        return ricettaRepository.findByCuoco(cuoco);
    }

    @Transactional
    public Ricetta findById(Long id) {
        return ricettaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Iterable<Ricetta> findAll() {
        return ricettaRepository.findAll();
    }

    @Transactional
    public Ricetta saveRicetta(Ricetta ricetta) {
        return ricettaRepository.save(ricetta);
    }

    @Transactional
    public void deleteRicetta(Long id) {
        ricettaRepository.deleteById(id);
    }

    @Transactional
    public long countRicette() {
        return ricettaRepository.count();
    }
}

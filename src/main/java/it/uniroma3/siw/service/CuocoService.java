package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.CuocoRepository;

@Service
public class CuocoService {
    @Autowired
    private CuocoRepository cuocoRepository;

    public Cuoco findById(Long id) {
        return cuocoRepository.findById(id).orElse(null);
    }

    public Iterable<Cuoco> findAll() {
        return cuocoRepository.findAll();
    }

    public Cuoco saveCuoco(Cuoco cuoco) {
        return cuocoRepository.save(cuoco);
    }

    public Optional<Cuoco> getCuoco(Long id) {
        return cuocoRepository.findById(id);
    }

    public Iterable<Cuoco> getAllCuochi() {
        return cuocoRepository.findAll();
    }

    public void deleteCuoco(Long id) {
        cuocoRepository.deleteById(id);
    }
    
    public Cuoco findByUtente(Utente utente) {
        return cuocoRepository.findByUtente(utente);
    }
}

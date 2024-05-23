package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.repository.IngredienteRepository;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Transactional
    public Ingrediente findByNome(String nome) {
        return ingredienteRepository.findByNome(nome);
    }

    @Transactional
    public Ingrediente saveIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }
    
    @Transactional
    public Optional<Ingrediente> findById(Long id) {
        return ingredienteRepository.findById(id);
    }

    @Transactional
    public List<Ingrediente> findAll() {
        return (List<Ingrediente>) ingredienteRepository.findAll();
    }
}

package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.repository.CuocoRepository;

@Service
public class CuocoService {

	@Autowired
	private CuocoRepository cuocoRepository;

	@Transactional
	public Cuoco findById(Long id) {
		return cuocoRepository.findById(id).orElse(null);
	}

	@Transactional
	public Iterable<Cuoco> findAll() {
		return cuocoRepository.findAll();
	}
	
	@Transactional
	public Boolean existsById(Long id) {
		return (cuocoRepository.findById(id).orElse(null))!=null;
	}
	
	@Transactional
	public Cuoco saveCuoco(Cuoco cuoco) {
		return cuocoRepository.save(cuoco);
	}

	@Transactional
	public Cuoco getCuoco(Long id) {
		return cuocoRepository.findById(id).orElse(null);
	}

	@Transactional
	public Iterable<Cuoco> getAllCuochi() {
		return cuocoRepository.findAll();
	}

	@Transactional
	public void deleteCuoco(Long id) {
		cuocoRepository.deleteById(id);
	}

	@Transactional
	public Cuoco findByUsername(String username) {
		return cuocoRepository.findByCredenzialiUsername(username);
	}
	
	@Transactional
    public long countCuochi() {
        return cuocoRepository.count();
    }
}

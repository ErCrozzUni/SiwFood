package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.RigaRicetta;
import it.uniroma3.siw.repository.RigaRicettaRepository;

@Service
public class RigaRicettaService {

    @Autowired
    private RigaRicettaRepository rigaRicettaRepository;

    @Transactional
    public RigaRicetta saveRigaRicetta(RigaRicetta rigaRicetta) {
        return rigaRicettaRepository.save(rigaRicetta);
    }
}

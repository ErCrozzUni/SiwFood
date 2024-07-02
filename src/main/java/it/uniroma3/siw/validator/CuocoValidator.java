package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.repository.CuocoRepository;
import it.uniroma3.siw.service.CuocoService;

@Component
public class CuocoValidator implements Validator {
    
    @Autowired
    private CuocoService cuocoService;
    
    @Autowired
    private CuocoRepository cuocoRepository;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Cuoco.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Cuoco cuoco = (Cuoco)o;
        if (cuocoRepository.findById(cuoco.getId()).isPresent() && cuoco.getDataDiNascita() != null && cuocoService.existsById(cuoco.getId())){
            errors.reject("cuoco.duplicate");
        }
    }
}

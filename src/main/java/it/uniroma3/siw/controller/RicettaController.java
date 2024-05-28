package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.model.RigaRicetta;
import it.uniroma3.siw.service.CuocoService;
import it.uniroma3.siw.service.IngredienteService;
import it.uniroma3.siw.service.RicettaService;

@Controller
@RequestMapping("/ricette")
public class RicettaController {

    @Autowired
    private RicettaService ricettaService;

    @Autowired
    private CuocoService cuocoService;
    
    @Autowired
    private IngredienteService ingredienteService;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // Mostra tutte le ricette
    @GetMapping
    public String showRicette(Model model) {
        model.addAttribute("ricette", ricettaService.findAll());
        return "user/ricette"; // restituisce il nome della vista
    }

    // Mostra una singola ricetta per ID
    @GetMapping("/{id}")
    public String getRicetta(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ricetta", this.ricettaService.findById(id));
        return "user/ricetta"; // restituisce il nome della vista
    }

    // Visualizza il form per aggiungere una nuova ricetta
    @GetMapping("/new")
    public String showNewRicettaForm(Model model) {
        model.addAttribute("ricetta", new Ricetta());
        model.addAttribute("ingredienti", ingredienteService.findAll());
        return "cuoco/formNewRicetta"; // restituisce il nome della vista per il form
    }

    // Salva una nuova ricetta
    @PostMapping("/new")
    public String saveRicetta(@RequestParam String nome, 
                              @RequestParam String descrizione, 
                              @RequestParam MultipartFile immagine,
                              @RequestParam List<Long> ingredientiId,
                              @RequestParam List<String> quantita,
                              Model model) {
        // Salva l'immagine
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIR, immagine.getOriginalFilename());
        fileNames.append(immagine.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, immagine.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crea e salva la nuova ricetta
        Ricetta ricetta = new Ricetta();
        ricetta.setNome(nome);
        ricetta.setDescrizione(descrizione);
        ricetta.setImmagine("/uploads/" + immagine.getOriginalFilename());
        ricetta.setCuoco(getCurrentLoggedInCuoco());
        ricettaService.saveRicetta(ricetta);

        // Aggiungi ingredienti alla ricetta
        for (int i = 0; i < ingredientiId.size(); i++) {
            Ingrediente ingrediente = ingredienteService.findById(ingredientiId.get(i)).orElse(null);
            if (ingrediente != null) {
                RigaRicetta rigaRicetta = new RigaRicetta();
                rigaRicetta.setIngrediente(ingrediente);
                rigaRicetta.setQuantita(quantita.get(i));
                rigaRicetta.setRicetta(ricetta);
                ricetta.getRigheRicetta().add(rigaRicetta); // Usa il getter per ottenere la lista
            }
        }

        ricettaService.saveRicetta(ricetta);
        return "redirect:/cuoco/indexCuoco"; // reindirizza alla pagina delle ricette del cuoco
    }

    // Elimina una ricetta per ID
    @GetMapping("/delete/{id}")
    public String deleteRicetta(@PathVariable("id") Long id) {
        ricettaService.deleteRicetta(id);
        return "redirect:/cuoco/indexCuoco"; // reindirizza alla lista delle ricette
    }

    // Ottiene l'utente loggato attualmente
    private Cuoco getCurrentLoggedInCuoco() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Credenziali credenziali = (Credenziali) authentication.getPrincipal();
        return cuocoService.findByUsername(credenziali.getUsername());
    }
}
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

    private static String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // Mostra tutte le ricette
    @GetMapping
    public String showRicette(Model model) {
        model.addAttribute("ricette", ricettaService.findAll());
        return "ricette"; // restituisce il nome della vista
    }

    // Mostra una singola ricetta per ID
    @GetMapping("/{id}")
    public String getRicetta(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ricetta", this.ricettaService.findById(id));
        return "ricetta"; // restituisce il nome della vista
    }

    // Visualizza il form per aggiungere una nuova ricetta
    @GetMapping("/new")
    public String showNewRicettaForm(Model model) {
        model.addAttribute("ricetta", new Ricetta());
        return "formNewRicetta"; // restituisce il nome della vista per il form
    }

    // Salva una nuova ricetta
    @PostMapping("/new")
    public String saveRicetta(@RequestParam String nome, @RequestParam String descrizione,
                              @RequestParam MultipartFile immagine,
                              @RequestParam("ingredienti[]") List<String> ingredienti,
                              @RequestParam("quantita[]") List<String> quantita,
                              Model model) {
        Ricetta ricetta = new Ricetta();
        ricetta.setNome(nome);
        ricetta.setDescrizione(descrizione);

        // Salva l'immagine
        if (!immagine.isEmpty()) {
            try {
                Path fileNameAndPath = Paths.get(UPLOAD_DIR, immagine.getOriginalFilename());
                Files.write(fileNameAndPath, immagine.getBytes());
                ricetta.setImmagine("/uploads/" + immagine.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Imposta il cuoco attuale
        Cuoco cuoco = getCurrentLoggedInCuoco();
        ricetta.setCuoco(cuoco);

        // Aggiunge gli ingredienti
        for (int i = 0; i < ingredienti.size(); i++) {
            Ingrediente ingrediente = ingredienteService.findByNome(ingredienti.get(i));
            if (ingrediente == null) {
                ingrediente = new Ingrediente();
                ingrediente.setNome(ingredienti.get(i));
                ingrediente = ingredienteService.saveIngrediente(ingrediente);
            }
            RigaRicetta rigaRicetta = new RigaRicetta();
            rigaRicetta.setIngrediente(ingrediente);
            rigaRicetta.setQuantita(quantita.get(i));
            ricetta.addRigaRicetta(rigaRicetta);
        }

        ricettaService.saveRicetta(ricetta);

        return "redirect:/success"; // Reindirizza alla pagina di successo
    }

    // Elimina una ricetta per ID
    @GetMapping("/delete/{id}")
    public String deleteRicetta(@PathVariable("id") Long id) {
        ricettaService.deleteRicetta(id);
        return "redirect:/ricette"; // reindirizza alla lista delle ricette
    }

    // Ottiene l'utente loggato attualmente
    private Cuoco getCurrentLoggedInCuoco() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Credenziali credenziali = (Credenziali) authentication.getPrincipal();
        return cuocoService.findByUtente(credenziali.getUtente());
    }
}

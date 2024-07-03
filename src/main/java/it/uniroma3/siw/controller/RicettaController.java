package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
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
import it.uniroma3.siw.service.RigaRicettaService;

@Controller
@RequestMapping("/ricette")
public class RicettaController {

    @Autowired
    private RicettaService ricettaService;

    @Autowired
    private CuocoService cuocoService;
    
    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private RigaRicettaService rigaRicettaService;
    
    private static final String UPLOAD_DIR = "src/main/resources/static/images/ricette/";

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

    @PostMapping("/newRicetta")
    public String createRicetta(
        @RequestParam("nome") String nome,
        @RequestParam("descrizione") String descrizione,
        @RequestParam("immagine") MultipartFile immagine,
        @RequestParam("ingredienti[]") List<String> ingredienti,
        @RequestParam("quantita[]") List<String> quantita,
        Principal principal,
        Model model) {
        // Implementazione del metodo
        // Salva l'immagine
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIR, immagine.getOriginalFilename());
        fileNames.append(immagine.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, immagine.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Ottieni il cuoco attualmente loggato
        String username = principal.getName();
        Credenziali credenziali = cuocoService.findByUsername(username).getCredenziali();
        Cuoco cuoco = credenziali.getCuoco();
        // Crea e salva la ricetta
        Ricetta ricetta = new Ricetta();
        ricetta.setNome(nome);
        ricetta.setDescrizione(descrizione);
        ricetta.setImmagine("/images/ricette/" + immagine.getOriginalFilename());
        ricetta.setCuoco(cuoco);
        ricettaService.saveRicetta(ricetta);        
        // Aggiungi ingredienti e righe ricetta
        for (int i = 0; i < ingredienti.size(); i++) {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setNome(ingredienti.get(i));
            ingredienteService.saveIngrediente(ingrediente);
            RigaRicetta rigaRicetta = new RigaRicetta();
            ricetta.addRigaRicetta(rigaRicetta);
            rigaRicetta.setIngrediente(ingrediente);
            rigaRicetta.setRicetta(ricetta);
            rigaRicetta.setQuantita(quantita.get(i));
            rigaRicettaService.saveRigaRicetta(rigaRicetta);
        }
        return "redirect:/"; // reindirizza alla pagina di successo
    }

    // Elimina una ricetta per ID
    @GetMapping("/delete/{id}")
    public String deleteRicetta(@PathVariable("id") Long id) {
        ricettaService.deleteRicetta(id);
        return "redirect:/admin/ricette"; // reindirizza alla lista delle ricette
    }

    // Ottiene l'utente loggato attualmente
    @SuppressWarnings("unused")
    private Cuoco getCurrentLoggedInCuoco() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Credenziali credenziali = (Credenziali) authentication.getPrincipal();
        return cuocoService.findByUsername(credenziali.getUsername());
    }
    
    @GetMapping("/admin/ricette")
    public String manageRicette(Model model) {
        model.addAttribute("ricette", ricettaService.findAll());
        return "admin/adminManageRicette";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditRicettaForm(@PathVariable("id") Long id, Model model) {
        Ricetta ricetta = ricettaService.findById(id);
        model.addAttribute("ricetta", ricetta);
        return "admin/editRicetta";
    }

    @PostMapping("/edit")
    public String editRicetta(@RequestParam("id") Long id,
                              @RequestParam("nome") String nome,
                              @RequestParam("descrizione") String descrizione,
                              @RequestParam("immagine") MultipartFile immagine,
                              @RequestParam("ingredienti[]") List<String> ingredienti,
                              @RequestParam("quantita[]") List<String> quantita,
                              Model model) {
        Ricetta ricetta = ricettaService.findById(id);
        ricetta.setNome(nome);
        ricetta.setDescrizione(descrizione);

        if (!immagine.isEmpty()) {
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIR, immagine.getOriginalFilename());
            fileNames.append(immagine.getOriginalFilename());
            try {
                Files.write(fileNameAndPath, immagine.getBytes());
                ricetta.setImmagine("/images/ricette/" + immagine.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Aggiorna gli ingredienti e le righe della ricetta
        ricetta.getRigheRicetta().clear();
        for (int i = 0; i < ingredienti.size(); i++) {
            Ingrediente ingrediente = ingredienteService.findByNome(ingredienti.get(i));
            if (ingrediente == null) {
                ingrediente = new Ingrediente();
                ingrediente.setNome(ingredienti.get(i));
                ingredienteService.saveIngrediente(ingrediente); // Salva l'ingrediente prima di utilizzarlo nella riga ricetta
            }
            
            RigaRicetta rigaRicetta = new RigaRicetta();
            rigaRicetta.setIngrediente(ingrediente);
            rigaRicetta.setQuantita(quantita.get(i));
            rigaRicetta.setRicetta(ricetta);
            ricetta.addRigaRicetta(rigaRicetta);
        }

        ricettaService.saveRicetta(ricetta);
        model.addAttribute("ricetta", ricetta);
        return "redirect:/admin/ricette";
    }

}
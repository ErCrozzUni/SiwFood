package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.CuocoService;
import it.uniroma3.siw.service.RicettaService;

@Controller
@RequestMapping("/cuochi")
public class CuocoController {

    @Autowired
    private CuocoService cuocoService;

    @Autowired
    private RicettaService ricettaService;
    
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // Mostra tutti i cuochi
    @GetMapping
    public String showCuochi(Model model) {
        model.addAttribute("cuochi", cuocoService.findAll());
        return "user/cuochi"; // restituisce il nome della vista
    }

    // Mostra un singolo cuoco per ID
    @GetMapping("/{id}")
    public String getCuoco(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cuoco", this.cuocoService.findById(id));
        return "user/cuoco"; // restituisce il nome della vista
    }

    // Visualizza il form per aggiungere un nuovo cuoco
    @GetMapping("/new")
    public String showNewCuocoForm(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        return "user/formNewCuoco"; // restituisce il nome della vista per il form
    }

    // Salva un nuovo cuoco
    @PostMapping
    public String saveCuoco(@RequestParam("nome") String nome,
                            @RequestParam("cognome") String cognome,
                            @RequestParam("dataDiNascita") String dataDiNascita,
                            @RequestParam("immagine") MultipartFile immagine) {
        // Salva l'immagine
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIR, immagine.getOriginalFilename());
        fileNames.append(immagine.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, immagine.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crea e salva il nuovo cuoco
        Cuoco cuoco = new Cuoco();
        cuoco.setNome(nome);
        cuoco.setCognome(cognome);
        cuoco.setDataDiNascita(LocalDate.parse(dataDiNascita)); // Conversione corretta
        cuoco.setImmagine("/uploads/" + immagine.getOriginalFilename()); // Imposta il percorso dell'immagine

        cuocoService.saveCuoco(cuoco);
        return "redirect:/cuochi"; // Reindirizza alla lista dei cuochi
    }

    // Elimina un cuoco per ID
    @GetMapping("/delete/{id}")
    public String deleteCuoco(@PathVariable("id") Long id) {
        cuocoService.deleteCuoco(id);
        return "redirect:/cuochi"; // reindirizza alla lista dei cuochi
    }
    
    @GetMapping("/indexCuoco")
    public String showCuocoIndex(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = ((UserDetails) authentication.getPrincipal()).getUsername();
            Cuoco cuoco = cuocoService.findByUsername(currentUserName);
            List<Ricetta> ricette = ricettaService.findRicetteByCuoco(cuoco);
            model.addAttribute("ricette", ricette);
        }     
        return "cuoco/indexCuoco";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditCuocoForm(@PathVariable("id") Long id, Model model) {
        Cuoco cuoco = cuocoService.findById(id);
        model.addAttribute("cuoco", cuoco);
        return "admin/editCuoco";
    }

    @PostMapping("/edit")
    public String editCuoco(@RequestParam("id") Long id,
                            @RequestParam("nome") String nome,
                            @RequestParam("cognome") String cognome,
                            @RequestParam("email") String email,
                            @RequestParam("dataDiNascita") String dataDiNascita,
                            @RequestParam("immagine") MultipartFile immagine,
                            @RequestParam("descrizione") String descrizione,
                            Model model) {
        Cuoco cuoco = cuocoService.findById(id);
        cuoco.setNome(nome);
        cuoco.setCognome(cognome);
        cuoco.setEmail(email);
        cuoco.setDataDiNascita(LocalDate.parse(dataDiNascita));
        cuoco.setDescrizione(descrizione);

        if (!immagine.isEmpty()) {
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIR, immagine.getOriginalFilename());
            fileNames.append(immagine.getOriginalFilename());
            try {
                Files.write(fileNameAndPath, immagine.getBytes());
                cuoco.setImmagine("/images/cuochi/" + immagine.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cuocoService.saveCuoco(cuoco);
        model.addAttribute("cuoco", cuoco);
        return "redirect:/admin/cuochi";
    }
}

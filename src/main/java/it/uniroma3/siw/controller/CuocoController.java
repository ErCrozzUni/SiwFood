package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.service.CuocoService;

@Controller
@RequestMapping("/cuochi")
public class CuocoController {

    @Autowired
    private CuocoService cuocoService;

    private static String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // Mostra tutti i cuochi
    @GetMapping
    public String showCuochi(Model model) {
        model.addAttribute("cuochi", cuocoService.findAll());
        return "cuochi"; // restituisce il nome della vista
    }

    // Mostra un singolo cuoco per ID
    @GetMapping("/{id}")
    public String getCuoco(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cuoco", this.cuocoService.findById(id));
        return "cuoco"; // restituisce il nome della vista
    }

    // Visualizza il form per aggiungere un nuovo cuoco
    @GetMapping("/new")
    public String showNewCuocoForm(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        return "formNewCuoco"; // restituisce il nome della vista per il form
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
        cuoco.setDataDiNascita(dataDiNascita);
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
}

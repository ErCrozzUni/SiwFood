package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.CuocoService;
import it.uniroma3.siw.service.RicettaService;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class AuthenticationController {

    @Autowired
    private CredenzialiService credenzialiService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CuocoService cuocoService;
    
    @Autowired
    private RicettaService ricettaService;

    @GetMapping(value = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("utente", new Utente());
        model.addAttribute("credenziali", new Credenziali());
        return "register";
    }

    @GetMapping(value = "/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @GetMapping(value = "/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "index";
        } else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credenziali credenziali = credenzialiService.getCredenziali(userDetails.getUsername());
            if (credenziali.getRuolo().equals(Credenziali.ADMIN_ROLE)) {
                return "indexAmministratore";
            }
        }
        return "index";
    }

    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credenziali credenziali = credenzialiService.getCredenziali(userDetails.getUsername());
        if (credenziali.getRuolo().equals(Credenziali.ADMIN_ROLE)) {
            return "indexAmministratore";
        }
        Cuoco cuoco = cuocoService.findByUtente(credenziali.getUtente());
        model.addAttribute("ricette", ricettaService.findRicetteByCuoco(cuoco));
        return "success";
    }


    @PostMapping(value = { "/register" })
    public String registerUser(@RequestParam("nome") String nome,
                               @RequestParam("cognome") String cognome,
                               @RequestParam("email") String email,
                               @RequestParam("dataDiNascita") String dataDiNascita,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("immagine") MultipartFile immagine,
                               @RequestParam("descrizione") String descrizione,
                               Model model) {
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setEmail(email);
        utente.setDataDiNascita(LocalDate.parse(dataDiNascita));

        Credenziali credenziali = new Credenziali();
        credenziali.setUsername(username);
        credenziali.setPassword(password);
        credenziali.setRuolo(Credenziali.DEFAULT_ROLE);
        credenziali.setUtente(utente);

        Cuoco cuoco = new Cuoco();
        cuoco.setNome(nome);
        cuoco.setCognome(cognome);
        cuoco.setDataDiNascita(dataDiNascita);
        cuoco.setDescrizione(descrizione);
        try {
            cuoco.setImmagine("/uploads/" + immagine.getOriginalFilename());
            Path fileNameAndPath = Paths.get("src/main/resources/static/uploads/", immagine.getOriginalFilename());
            Files.write(fileNameAndPath, immagine.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cuoco.setUtente(utente);

        utente.setCredenziali(credenziali);
        utente.setCuoco(cuoco);

        utenteService.saveUser(utente);
        credenzialiService.saveCredenziali(credenziali);
        cuocoService.saveCuoco(cuoco);

        model.addAttribute("utente", utente);
        return "registrationSuccessful";
    }

}

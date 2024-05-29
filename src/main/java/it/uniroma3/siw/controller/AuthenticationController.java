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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.CuocoService;

@Controller
public class AuthenticationController {

    @Autowired
    private CredenzialiService credenzialiService;

    @Autowired
    private CuocoService cuocoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/cuochi/";

    @GetMapping(value = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("cuoco", new Cuoco());
        model.addAttribute("credenziali", new Credenziali());
        return "user/register";
    }

    @GetMapping(value = "/login")
    public String showLoginForm(Model model) {
        return "user/login";
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "user/index";
        } else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credenziali credenziali = credenzialiService.getCredenziali(userDetails.getUsername());
            if (credenziali.getRuolo().equals(Credenziali.ADMIN_ROLE)) {
                return "admin/adminIndex";
            }
        }
        return "user/index";
    }

    @GetMapping(value = "/success")
    public String success(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "user/index";
        } else {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credenziali credenziali = credenzialiService.getCredenziali(userDetails.getUsername());
            if (credenziali.getRuolo().equals(Credenziali.ADMIN_ROLE)) {
                return "admin/adminIndex";
            }
        }
        return "user/index";
    }
    
    @GetMapping(value = "/indexCuoco")
    public String defaultAfterLogin(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credenziali credenziali = credenzialiService.getCredenziali(userDetails.getUsername());
        if (credenziali.getRuolo().equals(Credenziali.ADMIN_ROLE)) {
            return "admin/adminIndex";
        }
        return "cuoco/indexCuoco";
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
        // Salva l'immagine
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIR, immagine.getOriginalFilename());
        fileNames.append(immagine.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, immagine.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crea e salva il cuoco
        Cuoco cuoco = new Cuoco();
        cuoco.setNome(nome);
        cuoco.setCognome(cognome);
        cuoco.setEmail(email);
        cuoco.setDataDiNascita(LocalDate.parse(dataDiNascita));
        cuoco.setImmagine("/images/cuochi/" + immagine.getOriginalFilename());
        cuoco.setDescrizione(descrizione);

        Credenziali credenziali = new Credenziali();
        credenziali.setUsername(username);
        credenziali.setPassword(passwordEncoder.encode(password));
        credenziali.setRuolo(Credenziali.DEFAULT_ROLE);
        credenziali.setCuoco(cuoco);

        cuoco.setCredenziali(credenziali);

        cuocoService.saveCuoco(cuoco);

        model.addAttribute("cuoco", cuoco);
        return "user/registrationSuccessful";
    }
}

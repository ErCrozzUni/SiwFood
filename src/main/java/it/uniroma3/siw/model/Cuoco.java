package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cuoco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String immagine;
    private String descrizione; 

    @OneToMany(mappedBy = "cuoco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ricetta> ricette = new ArrayList<>();

    @OneToOne
    private Utente utente;

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Ricetta> getRicette() {
        return ricette;
    }

    public void setRicette(List<Ricetta> ricette) {
        this.ricette = ricette;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    // Metodi di utilità
    public void addRicetta(Ricetta ricetta) {
        ricette.add(ricetta);
        ricetta.setCuoco(this);
    }

    public void removeRicetta(Ricetta ricetta) {
        ricette.remove(ricetta);
        ricetta.setCuoco(null);
    }
}

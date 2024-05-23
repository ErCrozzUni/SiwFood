package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ricetta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;
    private String immagine;

    @ManyToOne
    private Cuoco cuoco;

    @OneToMany(mappedBy = "ricetta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RigaRicetta> righeRicetta = new ArrayList<>();

    // Getters e setters
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public Cuoco getCuoco() {
        return cuoco;
    }

    public void setCuoco(Cuoco cuoco) {
        this.cuoco = cuoco;
    }

    public List<RigaRicetta> getRigheRicetta() {
        return righeRicetta;
    }

    public void setRigheRicetta(List<RigaRicetta> righeRicetta) {
        this.righeRicetta = righeRicetta;
    }

    // Metodi di utilità
    public void addRigaRicetta(RigaRicetta rigaRicetta) {
        righeRicetta.add(rigaRicetta);
        rigaRicetta.setRicetta(this);
    }

    public void removeRigaRicetta(RigaRicetta rigaRicetta) {
        righeRicetta.remove(rigaRicetta);
        rigaRicetta.setRicetta(null);
    }
}

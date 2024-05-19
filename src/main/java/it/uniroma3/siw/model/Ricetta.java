package it.uniroma3.siw.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ricetta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String immagine;

    @OneToMany(mappedBy = "ricetta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RigaRicetta> ingredienti;

    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "cuoco_id")
    private Cuoco cuoco;

    // Costruttori
    public Ricetta() {}

    public Ricetta(String nome, String immagine, String descrizione, Cuoco cuoco) {
        this.nome = nome;
        this.immagine = immagine;
        this.descrizione = descrizione;
        this.cuoco = cuoco;
    }

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

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public List<RigaRicetta> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<RigaRicetta> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Cuoco getCuoco() {
        return cuoco;
    }

    public void setCuoco(Cuoco cuoco) {
        this.cuoco = cuoco;
    }

    // Metodi di utilità
    public void addRigaRicetta(RigaRicetta rigaRicetta) {
        ingredienti.add(rigaRicetta);
        rigaRicetta.setRicetta(this);
    }

    public void removeRigaRicetta(RigaRicetta rigaRicetta) {
        ingredienti.remove(rigaRicetta);
        rigaRicetta.setRicetta(null);
    }
}
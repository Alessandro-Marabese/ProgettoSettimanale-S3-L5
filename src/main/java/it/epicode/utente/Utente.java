package it.epicode.utente;

import it.epicode.prestito.Prestito;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private int numeroTessera;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String cognome;

    @Column
    private LocalDate dataNascita;

    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti = new ArrayList<>();

    public Utente() {
    }

    public Utente(int numeroTessera, String nome, String cognome, LocalDate dataNascita) {
        this.numeroTessera = numeroTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public int getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(int numeroTessera) {
        this.numeroTessera = numeroTessera;
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

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }
}

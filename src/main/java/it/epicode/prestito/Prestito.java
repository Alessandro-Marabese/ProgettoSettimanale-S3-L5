package it.epicode.prestito;

import it.epicode.elementobiblioteca.ElementoBiblioteca;
import it.epicode.utente.Utente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prestito")
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToMany
    @JoinTable(name = "prestito_elemento_biblioteca",
               joinColumns = @JoinColumn(name = "prestito_id"),
               inverseJoinColumns = @JoinColumn(name = "elemento_biblioteca_id"))
    private Set<ElementoBiblioteca> elementoBiblioteca;

    @Column(nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column(nullable = false)
    private LocalDate dataRestituzioneEffettiva;


    public Prestito() {
    }

    public Prestito(Long id, Utente utente, Set<ElementoBiblioteca> elementoBiblioteca, LocalDate dataInizioPrestito, LocalDate dataRestituzionePrevista, LocalDate dataRestituzioneEffettiva) {
        this.id = id;
        this.utente = utente;
        this.elementoBiblioteca = elementoBiblioteca;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataRestituzionePrevista;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Set<ElementoBiblioteca> getElementoBiblioteca() {
        return elementoBiblioteca;
    }

    public void setElementoBiblioteca(Set<ElementoBiblioteca> elementoBiblioteca) {
        this.elementoBiblioteca = elementoBiblioteca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }
}

package it.epicode.elementobiblioteca;

import it.epicode.prestito.Prestito;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ElementoBiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codiceISBN;

    @Column(length = 50, nullable = false)
    private String titolo;

    @Column(length = 50, nullable = false)
    private int annoPubblicazione;

    @Column(length = 50, nullable = false)
    private int numeroPagine;

    @ManyToMany(mappedBy = "elementoBiblioteca")
    private Set<Prestito> prestiti;

    public Long getCodiceISBN() {
        return codiceISBN;
    }

    public void setCodiceISBN(Long codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public ElementoBiblioteca() {
    }

    public ElementoBiblioteca(Long codiceISBN, String titolo, int annoPubblicazione, int numeroPagine) {
        this.codiceISBN = codiceISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }
}

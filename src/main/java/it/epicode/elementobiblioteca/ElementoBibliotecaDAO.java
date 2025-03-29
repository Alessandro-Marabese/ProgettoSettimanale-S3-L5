package it.epicode.elementobiblioteca;

import it.epicode.elementobiblioteca.exception.BibliotecaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;

import java.util.List;

public class ElementoBibliotecaDAO {
    private EntityManager em;

    public ElementoBibliotecaDAO(EntityManager em) {
        this.em = em;
    }

    public void update(ElementoBiblioteca e) {
        try {
            em.merge(e);
        } catch(PersistenceException ex) {
            throw new BibliotecaException("Errore durante l'aggiornamento dell'elemento" + ex);
        }
    }

    public ElementoBiblioteca getById(long id) {
        return em.find(ElementoBiblioteca.class, id);
    }

    public void aggiungiElemento(ElementoBiblioteca e) {
        try {
            em.persist(e);
        } catch (PersistenceException ex) {
            throw new BibliotecaException("Errore durante l'aggiunta dell'elemento" + ex);
        }
    }

    public ElementoBiblioteca rimuoviElemento(String codiceISBN) {
        try {
            ElementoBiblioteca elemento = em.createQuery("select e from ElementoBiblioteca e where e.codiceISBN = :codiceISBN", ElementoBiblioteca.class)
                    .setParameter("codiceISBN", codiceISBN)
                    .getSingleResult();
            if (elemento != null) em.remove(elemento);
            return elemento;
        } catch (NoResultException ex) {
            throw new BibliotecaException("Nessun elemento trovato con codice ISBN " + codiceISBN);
        } catch (PersistenceException ex) {
            throw new BibliotecaException("Errore durante la rimozione dell'elemento" + ex);
        }
    }

    public ElementoBiblioteca ricercaPerISBN(String codiceISBN) {
        try {
            return em.createQuery("select e from ElementoBiblioteca e where e.codiceISBN = :codiceISBN", ElementoBiblioteca.class)
                    .setParameter("codiceISBN", codiceISBN)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new BibliotecaException("Nessun elemento trovato con codice ISBN " + codiceISBN);
        } catch (PersistenceException ex) {
            throw new BibliotecaException("Errore durante la ricerca per codice ISBN" + ex);
        }
    }

    public List<ElementoBiblioteca> ricercaPerAnno(int anno) {
        try {
            return em.createQuery("select e from ElementoBiblioteca e where e.annoPubblicazione = :anno", ElementoBiblioteca.class)
                    .setParameter("anno", anno)
                    .getResultList();
        } catch (PersistenceException ex) {
            throw new BibliotecaException("Errore durante la ricerca per anno" + ex);
        }
    }

    public List<ElementoBiblioteca> ricercaPerAutore(String autore) {
        try {
            return em.createQuery("select e from ElementoBiblioteca e where e.autore = :autore", ElementoBiblioteca.class)
                    .setParameter("autore", autore)
                    .getResultList();
        } catch (PersistenceException ex) {
            throw new BibliotecaException("Errore durante la ricerca per autore" + ex);
        }
    }

    public List<ElementoBiblioteca> ricercaPerTitolo(String titolo) {
        try {
            return em.createQuery("select e from ElementoBiblioteca e where e.titolo like :titolo", ElementoBiblioteca.class)
                    .setParameter("titolo", "%" + titolo + "%")
                    .getResultList();
        } catch (PersistenceException ex) {
            throw new BibliotecaException("Errore durante la ricerca per titolo" + ex);
        }
    }

}

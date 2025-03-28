package it.epicode.elementobiblioteca;

import jakarta.persistence.EntityManager;

import java.util.List;

public class ElementoBibliotecaDAO {
    private EntityManager em;

    public ElementoBibliotecaDAO(EntityManager em) {
        this.em = em;
    }

    public void update(ElementoBiblioteca e) {
        em.merge(e);
    }

    public ElementoBiblioteca getById(long id) {
        return em.find(ElementoBiblioteca.class, id);
    }

    public void aggiungiElemento(ElementoBiblioteca e) {
        em.persist(e);
    }

    public void rimuoviElemento(String codiceISBN) {
        ElementoBiblioteca e = em.find(ElementoBiblioteca.class, codiceISBN);
        if(e != null) {
            em.remove(e);
        }
    }

    public ElementoBiblioteca ricercaPerISBN(String codiceISBN) {
        return em.find(ElementoBiblioteca.class, codiceISBN);
    }

    public List<ElementoBiblioteca> ricercaPerAnno(int anno) {
        return em.createQuery("select e from ElementoBiblioteca e where e.annoPubblicazione = :anno", ElementoBiblioteca.class)
                .setParameter("anno", anno)
                .getResultList();
    }

    public List<ElementoBiblioteca> ricercaPerAutore(String autore) {
        return em.createQuery("select e from ElementoBiblioteca e where e.autore = :autore", ElementoBiblioteca.class)
                .setParameter("autore", autore)
                .getResultList();
    }

    public List<ElementoBiblioteca> ricercaPerTitolo(String titolo) {
        return em.createQuery("select e from ElementoBiblioteca e where e.titolo like :titolo", ElementoBiblioteca.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
    }
}

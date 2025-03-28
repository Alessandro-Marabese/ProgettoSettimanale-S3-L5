package it.epicode.utente;

import jakarta.persistence.EntityManager;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiUtente(Utente u) {
        em.persist(u);
    }

    public Utente getById(Long id) {
        return em.find(Utente.class, id);
    }

    public void update(Utente u) {
        em.merge(u);
    }

    public void delete(Long id) {
        Utente u = getById(id);
        em.remove(u);
    }
}

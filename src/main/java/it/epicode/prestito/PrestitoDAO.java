package it.epicode.prestito;

import jakarta.persistence.EntityManager;

import java.util.List;

public class PrestitoDAO {
    private EntityManager em;
    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiPrestito(Prestito p) {
        em.persist(p);
    }

    public void update(Prestito p) {
        em.merge(p);
    }

    public Prestito getById(Long id) {
        return em.find(Prestito.class, id);
    }

    public void delete(Long id) {
        Prestito p = getById(id);
        em.remove(p);
    }

    public List<Prestito> ricercaPrestitiUtente(Long numeroTessera) {
        return em.createQuery("select p from Prestito p where p.utente.numeroTessera = :numeroTessera", Prestito.class)
                .setParameter("numeroTessera", numeroTessera)
                .getResultList();
    }

    public List<Prestito> ricercaPrestitiScaduti() {
        return em.createNamedQuery("prestiti_scaduti.find", Prestito.class).getResultList();
    }


}

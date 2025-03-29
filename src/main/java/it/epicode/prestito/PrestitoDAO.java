package it.epicode.prestito;

import it.epicode.elementobiblioteca.exception.PrestitoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.util.List;

public class PrestitoDAO {
    private EntityManager em;
    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiPrestito(Prestito p) {
        try {
            em.persist(p);
        } catch (PersistenceException ex) {
            throw new PrestitoException("Errore durante l'aggiunta del prestito" + ex);
        }
    }

    public void update(Prestito p) {
        try {
            em.merge(p);
        } catch (PersistenceException ex) {
            throw new PrestitoException("Errore durante l'aggiornamento del prestito" + ex);
        }
    }

    public Prestito getById(Long id) {
        try {
            Prestito prestito = em.find(Prestito.class, id);
            if(prestito == null) {
                throw new PrestitoException("Prestito non trovato");
            } else {
                return prestito;
            }
        } catch (PersistenceException ex) {
            throw new PrestitoException("Errore durante la ricerca del prestito" + ex);
        }
    }

    public void delete(Long id) {
        try {
            Prestito p = getById(id);
            if(p != null) {
                em.remove(p);
            } else {
                throw new PrestitoException("Prestito non trovato");
            }
        } catch (PersistenceException ex) {
            throw new PrestitoException("Errore durante la rimozione del prestito" + ex);
        }
    }

    public List<Prestito> ricercaPrestitiUtente(int numeroTessera) {
        try {
            return em.createQuery("select p from Prestito p where p.utente.numeroTessera = :numeroTessera", Prestito.class)
                    .setParameter("numeroTessera", numeroTessera)
                    .getResultList();
        } catch (PersistenceException ex) {
            throw new PrestitoException("Errore durante la ricerca dei prestiti dell'utente" + ex);
        }
    }

    public List<Prestito> ricercaPrestitiScaduti() {
        try {
            return em.createNamedQuery("prestiti_scaduti.find", Prestito.class).getResultList();
        } catch (PersistenceException ex) {
            throw new PrestitoException("Errore durante la ricerca dei prestiti scaduti" + ex);
        }
    }


}

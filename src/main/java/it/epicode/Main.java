package it.epicode;


import it.epicode.elementobiblioteca.ElementoBibliotecaDAO;
import it.epicode.elementobiblioteca.libro.Libro;
import it.epicode.elementobiblioteca.rivista.Periodicità;
import it.epicode.elementobiblioteca.rivista.Rivista;

import it.epicode.prestito.PrestitoDAO;
import it.epicode.utente.Utente;
import it.epicode.utente.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca-S3-L5");
        EntityManager em = emf.createEntityManager();

        ElementoBibliotecaDAO elementodao = new ElementoBibliotecaDAO(em);
        UtenteDAO utentedao = new UtenteDAO(em);
        PrestitoDAO prestitodao = new PrestitoDAO(em);

        Utente u1 = new Utente(1, "Giovanni", "Rossi", LocalDate.of(2000, 1, 1));
        Utente u2 = new Utente(2, "Maria", "Verdi", LocalDate.of(1999, 2, 2));
        Utente u3 = new Utente(3, "Luigi", "Bianchi", LocalDate.of(1998, 3, 3));
        Utente u4 = new Utente(4, "Giuseppe", "Neri", LocalDate.of(1997, 4, 4));

        Libro libro1 = new Libro("1234567890", "Il Signore degli Anelli", 1954, 1000, "JRR Tolkien", "Fantasy");
        Libro libro2 = new Libro("1234567891", "1984", 1950, 500, "George Orwell", "Fantascienza");
        Libro libro3 = new Libro("1234567892", "Il nome della rosa", 1980, 1000, "Umberto Eco", "Giallo" );
        Libro libro4 = new Libro("1234567893", "Il cimitero di Praga", 1990, 300, "Umberto Eco", "Storico");
        Libro libro5 = new Libro("1234567894", "Il Silmarillion", 1954, 1000, "JRR Tolkien", "Fantasy");

        Rivista rivista1 = new Rivista("1234567895", "La settimana enigmistica", 1960, 30, Periodicità.SETTIMANALE);
        Rivista rivista2 = new Rivista("1234567896", "La Stampa", 1954, 100, Periodicità.SETTIMANALE);
        Rivista rivista3 = new Rivista("1234567897", "Sports Illustrated", 1970, 50, Periodicità.MENSILE);
        Rivista rivista4 = new Rivista("1234567898", "National geographic Magazine", 1960, 50, Periodicità.MENSILE);
        Rivista rivista5 = new Rivista("1234567899", "Focus", 1990, 100, Periodicità.SEMESTRALE);

        em.getTransaction().begin();
        utentedao.aggiungiUtente(u1);
        utentedao.aggiungiUtente(u2);
        utentedao.aggiungiUtente(u3);
        utentedao.aggiungiUtente(u4);

        elementodao.aggiungiElemento(libro1);
        elementodao.aggiungiElemento(libro2);
        elementodao.aggiungiElemento(libro3);
        elementodao.aggiungiElemento(libro4);
        elementodao.aggiungiElemento(libro5);
        elementodao.aggiungiElemento(rivista1);
        elementodao.aggiungiElemento(rivista2);
        elementodao.aggiungiElemento(rivista3);
        elementodao.aggiungiElemento(rivista4);
        elementodao.aggiungiElemento(rivista5);

        em.getTransaction().commit();







        em.close();
        emf.close();



    }
}

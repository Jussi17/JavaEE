package jpaluokat;

import java.util.List;
import javax.persistence.*;

public class JPAsimplekoe {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    public static void initEntityManager() throws Exception {
        emf = Persistence.createEntityManagerFactory("JPAsimplekoePU");
        em = emf.createEntityManager();
    }

    public static void closeEntityManager() {
        em.close();
        emf.close();
    }

    public static void initTransaction() {
        tx = em.getTransaction();
    }

    // Lisää kirja kirjaryhmään ja päivittää kirjojen lukumäärän
    public static void addBook(String title, String author, int bookgroupId) throws Exception {
        initTransaction();
        tx.begin();
        Bookgroup bg = em.find(Bookgroup.class, bookgroupId);
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setBookgroup(bg);
        em.persist(b);
        tx.commit();
        em.refresh(bg);
        System.out.println("Kirjaryhmässä '" + bg.getName() + "' on nyt " + bg.getBooks().size() + " kirjaa");
    }

    // Poistaa kirjan ja päivittää kirjojen lukumäärän
    public static void removeBook(int bookId) throws Exception {
        initTransaction();
        tx.begin();
        Book b = em.find(Book.class, bookId);
        if (b != null) {
            Bookgroup bg = b.getBookgroup();
            em.remove(b);
            tx.commit();
            em.refresh(bg);
            System.out.println("Kirja poistettu. Kirjaryhmässä '" + bg.getName() + "' on nyt " + bg.getBooks().size() + " kirjaa");
        } else {
            tx.rollback();
            System.out.println("Kirjaa ei löydy id:llä " + bookId);
        }
    }

    // Poistaa kirjaryhmän ja kaikki siihen kuuluvat kirjat
    public static void removeBookgroup(int bookgroupId) throws Exception {
        initTransaction();
        tx.begin();
        Bookgroup bg = em.find(Bookgroup.class, bookgroupId);
        if (bg != null) {
            System.out.println("Poistetaan kirjaryhmä '" + bg.getName() + "' ja sen " + bg.getBooks().size() + " kirjaa");
            em.remove(bg);
            tx.commit();
            System.out.println("Kirjaryhmä poistettu!");
        } else {
            tx.rollback();
            System.out.println("Kirjaryhmää ei löydy");
        }
    }

    // Näyttää kaikki kirjaryhmät ja niiden kirjat
    public static void showAll() throws Exception {
        @SuppressWarnings("unchecked")
        List<Bookgroup> groups = em.createNamedQuery("Bookgroup.findAll").getResultList();
        for (Bookgroup bg : groups) {
            System.out.println("\nKirjaryhmä: " + bg.getName());
            System.out.println("Kirjoja: " + bg.getBooks().size());
            for (Book b : bg.getBooks()) {
                System.out.println("  - " + b.getTitle() + " / " + b.getAuthor());
            }
        }
    }

    public static void main(String args[]) {
        try {
            initEntityManager();

            System.out.println("=== Kaikki kirjat ja ryhmät ===");
            showAll();

            System.out.println("\n=== Lisätään uusi kirja ===");
            addBook("Galaksien Valloittaja", "Seppo Avaruus", 1);

            System.out.println("\n=== Poistetaan kirja id=3 ===");
            removeBook(3);

            System.out.println("\n=== Poistetaan kirjaryhmä id=1 ===");
            removeBookgroup(1);

            System.out.println("\n=== Tilanne lopussa ===");
            showAll();

            closeEntityManager();
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
            e.printStackTrace();
        }
    }
}
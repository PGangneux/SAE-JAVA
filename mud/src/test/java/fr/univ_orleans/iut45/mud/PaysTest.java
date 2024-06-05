package fr.univ_orleans.iut45.mud;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class PaysTest {

    @Test
    public void nomPays() {
        Pays france = new Pays("France");
        Pays belgique = new Pays("Belgique");
        Pays suisse = new Pays("Suisse");
        Pays finlande = new Pays("Finlande");
        assertEquals(france.getNom(), "France");
        assertEquals(belgique.getNom(), "Belgique");
        assertEquals(suisse.getNom(), "Suisse");
        assertEquals(finlande.getNom(), "Finlande");
    }
}

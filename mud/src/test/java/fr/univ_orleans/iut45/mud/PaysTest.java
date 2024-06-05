package fr.univ_orleans.iut45.mud;
// import org.junit.*;
// import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaysTest {

    @Test
    public void nomPays() {
        Pays france = new Pays("France");
        Pays belgique = new Pays("Belgique");
        Pays suisse = new Pays("Suisse");
        Pays finlande = new Pays("Finlande");
        Assertions.assertEquals(france.getNom(), "France");
        Assertions.assertEquals(belgique.getNom(), "Belgique");
        Assertions.assertEquals(suisse.getNom(), "Suisse");
        Assertions.assertEquals(finlande.getNom(), "Finlande");
    }
}

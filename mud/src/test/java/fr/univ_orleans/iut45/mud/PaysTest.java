package fr.univ_orleans.iut45.mud;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @Test
    public void compteurMedaille(){
        Pays france = new Pays("France");
        Assertions.assertEquals(france.getCompteurMedaille(), 0);
        france.setCompteurMedaille(5);
        Assertions.assertEquals(france.getCompteurMedaille(), 5);
    }

    @Test
    public void compteurMedailleOr(){
        Pays france = new Pays("France");
        Assertions.assertEquals(france.getCompteurMedailleOr(), 0);
        france.setCompteurMedailleOr(5);
        Assertions.assertEquals(france.getCompteurMedailleOr(), 5);
    }


    @Test
    public void classement(){
        Set<Pays> ensPays = new HashSet<>();
        Pays france = new Pays("France");
        Pays belgique = new Pays("Belgique");
        Pays suisse = new Pays("Suisse");
        Pays finlande = new Pays("Finlande");
        ensPays.add(finlande);
        ensPays.add(france);
        ensPays.add(belgique);
        ensPays.add(suisse);

        france.setCompteurMedaille(5);
        belgique.setCompteurMedaille(15);
        suisse.setCompteurMedaille(5);

        france.setCompteurMedailleOr(5);
        belgique.setCompteurMedaille(2);
        suisse.setCompteurMedaille(1);

        List<Pays> classementMedaille = Pays.classementPaysMedaille(ensPays);
        List<Pays> classementMedailleOr = Pays.classementPaysMedailleOr(ensPays);

        List<Pays> classementMedailleTemoin = Arrays.asList(belgique,france,suisse,finlande);
        List<Pays> classementMedailleOrTemoin = Arrays.asList(france,belgique,suisse,finlande);

        Assertions.assertEquals(classementMedaille, classementMedailleTemoin);
        Assertions.assertEquals(classementMedailleOr, classementMedailleOrTemoin);
        
    }
}

package fr.univ_orleans.iut45.mud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
public class TestAthlete {
    private static Athlete athlete1;
    private static Athlete athlete2;
    private static Athlete athlete3;
    private static Athlete athlete4;
    private static Athlete athlete5;
    
    @BeforeAll // s'execute avant tous les test
    static void setUp() { //initialisation des attribut
        athlete1 = new Athlete("Randriantsoa", "Nathan", "M", null, null, 10, 20,65);
        athlete2 = new Athlete("Voivenel", "Romain", "M", null, null, 11, 21, 66);
        athlete3 = new Athlete("Gangneux", "Pierre", "M", null, null, 12, 22, 67);
        athlete4 = new Athlete("Saunier", "Léo", "M", null, null, 13, 23, 68);
        athlete5 = new Athlete("Random", "Fille", "F", null, null, 14, 24, 69);
    }

    @Test
    public void testNomPrenomSex() {
        //athlete 1
        Assertions.assertEquals(athlete1.getNom(), "Randriantsoa");
        Assertions.assertEquals(athlete1.getPrenom(), "Nathan");
        Assertions.assertEquals(athlete1.getSexe(), "M");

        //athlete 2
        Assertions.assertEquals(athlete2.getNom(), "Voivenel");
        Assertions.assertEquals(athlete2.getPrenom(), "Romain");
        Assertions.assertEquals(athlete2.getSexe(), "M");

        //athlete 5
        Assertions.assertEquals(athlete5.getNom(), "Random");
        Assertions.assertEquals(athlete5.getPrenom(), "Fille");
        Assertions.assertEquals(athlete5.getSexe(), "F");

    }

    @Test
    public void testInfos() {
        // initialisation de 2 pays France, Allemagne
        Pays france = new Pays("France");
        Pays allemage = new Pays("Allemagne");

        //mettre des pays pour les athlete
        athlete3.setPays(france);
        athlete4.setPays(allemage);

        //test
        Assertions.assertEquals(athlete3.getPays(), france);
        Assertions.assertEquals(athlete4.getPays().getNom(), "Allemagne");    
    }

    @Test
    public void testCapacite() {
        //init
        Integer forceAthlete2 = 11; Integer agiliteAthlete2 = 21; Integer endurenceAthlete2 = 66;
        Integer forceAthlete5 = 14; Integer agiliteAthlete5 = 24; Integer endurenceAthlete5 = 69;

        //test Athlete2
        Assertions.assertEquals(athlete2.getForce(), forceAthlete2);
        Assertions.assertEquals(athlete2.getAgilite(), agiliteAthlete2);
        Assertions.assertEquals(athlete2.getEndurance(), endurenceAthlete2);

        //test Athlete2
        Assertions.assertEquals(athlete5.getForce(), forceAthlete5);
        Assertions.assertEquals(athlete5.getAgilite(), agiliteAthlete5);
        Assertions.assertEquals(athlete5.getEndurance(), endurenceAthlete5);

        
    }
}
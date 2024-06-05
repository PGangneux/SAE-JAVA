package fr.univ_orleans.iut45.mud;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class TestAthlete {
    private Athlete athlete1;
    private Athlete athlete2;
    private Athlete athlete3;
    private Athlete athlete4;
    private Athlete athlete5;

    @Before // s'execute avant tous les test
    public void init() { //initialisation des attribut
        this.athlete1 = new Athlete("Randriantsoa", "Nathan", "M", null, null, 10, 20,65);
        this.athlete2 = new Athlete("Voivenel", "Romain", "M", null, null, 11, 21, 66);
        this.athlete3 = new Athlete("Gangneux", "Pierre", "M", null, null, 12, 22, 67);
        this.athlete4 = new Athlete("Saunier", "Léo", "M", null, null, 13, 23, 68);
        this.athlete5 = new Athlete("Random", "Fille", "F", null, null, 14, 24, 69);

    }

    @Test
    public void testNomPrenomSex() {
        //athlete 1
        assertEquals(athlete1.getNom(), "Randriantsoa");
        assertEquals(athlete1.getPrenom(), "Nathan");
        assertEquals(athlete1.getSexe(), "M");

        //athlete 2
        assertEquals(athlete2.getNom(), "Voivenel");
        assertEquals(athlete2.getPrenom(), "Romain");
        assertEquals(athlete2.getSexe(), "M");

        //athlete 5
        assertEquals(athlete5.getNom(), "Random");
        assertEquals(athlete5.getPrenom(), "Fille");
        assertEquals(athlete5.getSexe(), "F");

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
        assertEquals(athlete3.getPays(), france);
        assertEquals(athlete4.getPays().getNom(), "Allemagne");    
    }

    @Test
    public void testCapacite() {
        //init
        Integer forceAthlete2 = 11; Integer agiliteAthlete2 = 21; Integer endurenceAthlete2 = 66;
        Integer forceAthlete5 = 14; Integer agiliteAthlete5 = 24; Integer endurenceAthlete5 = 69;

        //test Athlete2
        assertEquals(this.athlete2.getForce(), forceAthlete2);
        assertEquals(this.athlete2.getAgilite(), agiliteAthlete2);
        assertEquals(this.athlete2.getEndurance(), endurenceAthlete2);

        //test Athlete2
        assertEquals(this.athlete5.getForce(), forceAthlete5);
        assertEquals(this.athlete5.getAgilite(), agiliteAthlete5);
        assertEquals(this.athlete5.getEndurance(), endurenceAthlete5);

        
    }
}
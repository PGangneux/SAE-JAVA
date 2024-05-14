package fr.univ_orleans.iut45.mud;


import org.junit.*;
import fr.univ_orleans.iut45.mud.Athlete;
import static org.junit.Assert.assertEquals;



public class TestAthlete {
    @Test
    public void testNom(){
        Athlete at = new Athlete("nom", "prenom", "sexe", null, null, 5, 5, 5);
        assertEquals(at.getNom(), "nom");
        Athlete at2 = new Athlete(null, "prenom", "sexe", null, null, 5, 5, 5);
        assertEquals(at2.getNom(), null);
    }

    @Test
    public void testPrenom(){
        Athlete at = new Athlete("nom", "prenom", "sexe", null, null, 5, 5, 5);
        assertEquals(at.getPrenom(), "prenom");
        Athlete at2 = new Athlete("nom", null, "sexe", null, null, 5, 5, 5);
        assertEquals(at2.getPrenom(), null);
    }

    @Test
    public void testSexe(){
        Athlete at = new Athlete("nom", "prenom", "H", null, null, 5, 5, 5);
        assertEquals(at.getSexe(), "H");
        Athlete at2 = new Athlete("nom", null, null, null, null, 5, 5, 5);
        assertEquals(at2.getSexe(), null);
    }

    @Test
    public void testPays(){
        Pays p = new Pays("France");
        Athlete at = new Athlete("nom", "prenom", "H", p, null, 5, 5, 5);
        assertEquals(at.getPays(), p);
        Athlete at2 = new Athlete("nom", null, null, null, null, 5, 5, 5);
        assertEquals(at2.getPays(), null);
    }


    @Test
    public void testSport(){
        Sport sp = new Sport("v");
        Athlete at = new Athlete("nom", "prenom", "H", null, sp, 5, 5, 5);
        assertEquals(at.getSport(), sp);
        Athlete at2 = new Athlete("nom", null, null, null, null, 5, 5, 5);
        assertEquals(at2.getSport(), null);
    }

    @Test
    public void capacite(){
        Athlete at = new Athlete("nom", "prenom", "H", null, null, 5, 15, 65);
        assertEquals(at.getForce(), 5);
        assertEquals(at.getAgilite(), 15);
        assertEquals(at.getEndurance(), 65);
        Athlete at2 = new Athlete("nom", null, null, null, null, null, null, null);
        assertEquals(at2.getSport(), null);
        
    }

}
package fr.univ_orleans.iut45.mud;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestEquipe {
    private static Equipe equipeTest1;
    private static Athlete athlete1;
    private static Athlete athlete2;
    private static Athlete athlete3;
    private static Pays paysE1;
    private static Sport vollley;

    @BeforeAll
    public static void setUp() {
        paysE1 = new Pays("France");
        vollley = new Sport("Volley");
        equipeTest1 = new Equipe("Equipe1", "M", paysE1, vollley);
        athlete1 = new Athlete("Randriantsoa", "Nathan", "M", paysE1, vollley, 10, 20,65);
        athlete2 = new Athlete("Voivenel", "Romain", "M", paysE1, vollley, 11, 21, 66);
        athlete3 = new Athlete("Gangneux", "Pierre", "M", paysE1, vollley, 12, 22, 67);
    }

    @Test
    public void testGetNom() {
        Assertions.assertEquals("Equipe1", equipeTest1.getNom());
    }

    @Test
    public void testSetNom() {
        equipeTest1.setNom("Equipe1Edited");
        Assertions.assertEquals("Equipe1Edited", equipeTest1.getNom());
    }

    @Test
    public void testGetSexe() {
        Assertions.assertEquals("M", equipeTest1.getSexe());
    }

    @Disabled
    public void testVerifSexe(){
        Assertions.assertEquals(true, athlete1);
        Assertions.assertEquals(true, athlete2);
        Assertions.assertEquals(true, athlete3);

    }

    @Disabled
    public void testAjouteAthlete() {
        equipeTest1.ajouteAthlete(athlete1);
        equipeTest1.ajouteAthlete(athlete2);
        equipeTest1.ajouteAthlete(athlete3);
        //A continuer 
        //Defini un equals et Hascode dans Athlete
    }

    @Disabled
    public void testSupAthlete() {
        equipeTest1.supAthlete(athlete3);
        //A continuer 
        //Defini un equals et Hascode dans Athlete
    }
}

package fr.univ_orleans.iut45.mud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveIndFem;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Pays;
import fr.univ_orleans.iut45.mud.items.Sport;

public class TestEpreuveIndFem {
    private static EpreuveIndFem epreuveIndFem;
    private static Sport volley;
    private static CompetInd competition;
    private static Athlete athlete1;
    private static Athlete athlete2;
    private static Athlete athlete3;
    private static Pays pays;

    @BeforeAll
    public static void setUp(){
        volley = new Sport("Volley");
        pays = new Pays("France");
        athlete1 = new Athlete("Personne", "Femme1", "F", pays, volley, 10, 20,65);
        athlete2 = new Athlete("Personne", "Femme2", "F", pays, volley, 11, 21, 66);
        athlete3 = new Athlete("Personne", "Femme3", "F", pays, volley, 12, 22, 67);
        competition = new CompetInd("Compet", "F", volley);
        competition.participer(athlete1);
        competition.participer(athlete2);
        competition.participer(athlete3);
        epreuveIndFem = new EpreuveIndFem("EpreuveTest", competition);
        epreuveIndFem.setScore(athlete1, 1);
        epreuveIndFem.setScore(athlete2, 2);
        epreuveIndFem.setScore(athlete3, 3);
    }

    @Test
    public void testGetNom(){
        Assertions.assertEquals("EpreuveTest", epreuveIndFem.getNom());
    }

    @Test
    public void testGetScoreTheorique(){
        Assertions.assertEquals(epreuveIndFem.getScoreTheorique(athlete1), 95);
        Assertions.assertEquals(epreuveIndFem.getScoreTheorique(athlete2), 98);
        Assertions.assertEquals(epreuveIndFem.getScoreTheorique(athlete3), 101);
    }

    @Test
    public void testGetScore(){
        Assertions.assertEquals(epreuveIndFem.getScore(athlete1), 1);
        Assertions.assertEquals(epreuveIndFem.getScore(athlete2), 2);
        Assertions.assertEquals(epreuveIndFem.getScore(athlete3), 3);
    }
    
    @Test
    public void testSetScore(){
        epreuveIndFem.setScore(athlete1, 12);
        Assertions.assertEquals(epreuveIndFem.getScore(athlete1), 12);
    }

    @Test
    public void testClassementEpreuve(){

    }
}

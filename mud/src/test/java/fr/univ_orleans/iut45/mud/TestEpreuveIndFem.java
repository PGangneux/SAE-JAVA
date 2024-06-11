package fr.univ_orleans.iut45.mud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestEpreuveIndFem {
    private static EpreuveIndFem epreuveIndMasc;
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
        competition = new CompetInd("Compet", "M", volley);
        competition.participer(athlete1);
        competition.participer(athlete2);
        competition.participer(athlete3);
        epreuveIndMasc = new EpreuveIndFem("EpreuveTest", competition);
        epreuveIndMasc.setScore(athlete1, 1);
        epreuveIndMasc.setScore(athlete2, 2);
        epreuveIndMasc.setScore(athlete3, 3);
    }

    @Test
    public void testGetNom(){
        Assertions.assertEquals("EpreuveTest", epreuveIndMasc.getNom());
    }

    @Test
    public void testGetScoreTheorique(){
        Assertions.assertEquals(epreuveIndMasc.getScoreTheorique(athlete1), 95);
        Assertions.assertEquals(epreuveIndMasc.getScoreTheorique(athlete2), 98);
        Assertions.assertEquals(epreuveIndMasc.getScoreTheorique(athlete3), 101);
    }

    @Test
    public void testGetScore(){
        Assertions.assertEquals(epreuveIndMasc.getScore(athlete1), 1);
        Assertions.assertEquals(epreuveIndMasc.getScore(athlete2), 2);
        Assertions.assertEquals(epreuveIndMasc.getScore(athlete3), 3);
    }
    
    @Test
    public void  testSetScore(){
        epreuveIndMasc.setScore(athlete1, 12);
        Assertions.assertEquals(epreuveIndMasc.getScore(athlete1), 12);
    }
}

package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestcompetCoop {
    private static CompetCoop competCoop;
    private static List<List<Participant>> liEquipeCheck;
    private static List<Participant> equipeCheckTest;

    private static Pays paysE1;
    private static Sport vollley;
    private static Athlete athlete1;
    private static Athlete athlete2;
    private static Equipe equipeTest1;

    

    @BeforeAll
    public static void setUp() {
        competCoop = new CompetCoop("Volley Competition", "M", new Sport("Volley"));
        paysE1 = new Pays("France");
        vollley = new Sport("Volley");
        equipeTest1 = new Equipe("Equipe1", "M", paysE1, vollley);
        equipeTest1.ajouteAthlete(athlete1);
        equipeTest1.ajouteAthlete(athlete2);
        athlete1 = new Athlete("Randriantsoa", "Nathan", "M", paysE1, vollley, 10, 20,65);
        athlete2 = new Athlete("Voivenel", "Romain", "M", paysE1, vollley, 11, 21, 66);
        liEquipeCheck = new ArrayList<>();
        equipeCheckTest = Arrays.asList(athlete1,athlete2);
        liEquipeCheck.add(equipeCheckTest);
        
    }
    @Test
    public void ajouteEquipe() {
        competCoop.participer(equipeTest1);
    }

    @Test
    public void TestGetNomCompet() {
        Assertions.assertEquals(competCoop.getNom(), "Volley Competition");
    }

    @Test
    public void TestGetSexe() {
        Assertions.assertEquals(competCoop.getSexe(), "M");
    }

    @Test
    public void TestGetSport() {
        Assertions.assertEquals(competCoop.getSport().getNom(), "Volley");
    }

    @Test
    public void TestSetNom() {
        competCoop.setNom("Compet BeachVolley");
        Assertions.assertEquals(competCoop.getNom(), "Compet BeachVolley");

    }

    @Test
    public void TestGetParticipant() {
        Assertions.assertEquals(competCoop.getParticipant().equals(equipeCheckTest), true);
    }

    @Test
    public void TestParticipantPresent() {
        Assertions.assertEquals(competCoop.participantPresent(athlete1), true);
    }

    @Test
    public void TestSupParticipant() {
        competCoop.suppParticipant(equipeTest1);
        Assertions.assertEquals(competCoop.getParticipant().contains(equipeTest1), false);
    }

    @Disabled
    public void TestClassement() {
        Assertions.assertEquals(competCoop.classement().equals("t"), true);
    }

    @Disabled
    public void TestGetScore() {
        Assertions.assertEquals(competCoop.getScore(athlete1), 1.2);
    }

}
    
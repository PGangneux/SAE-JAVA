package fr.univ_orleans.iut45.mud;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Participant;
import fr.univ_orleans.iut45.mud.items.Pays;
import fr.univ_orleans.iut45.mud.items.Sport;

public class TestCompetInd {
    private static CompetInd competInd1;
    private static List<Participant> liParticipantCheck;
    private static Pays paysE1;
    private static Sport vollley;
    private static Athlete athlete1;
    private static Athlete athlete2;

    @BeforeAll
    public static void setUp() {
        competInd1 = new CompetInd("Volley Competition", "M", new Sport("Volley"));
        paysE1 = new Pays("France");
        vollley = new Sport("Volley");
        athlete1 = new Athlete("Randriantsoa", "Nathan", "M", paysE1, vollley, 10, 20,65);
        athlete2 = new Athlete("Voivenel", "Romain", "M", paysE1, vollley, 11, 21, 66);
        liParticipantCheck = Arrays.asList(athlete1,athlete2);
    }
    @Test
    public void ajouteAthlete() {
        competInd1.participer(athlete1);
        competInd1.participer(athlete2);
    }

    @Test
    public void TestGetNomCompet() {
        Assertions.assertEquals(competInd1.getNom(), "Volley Competition");
    }

    @Test
    public void TestGetSexe() {
        Assertions.assertEquals(competInd1.getSexe(), "M");
    }

    @Test
    public void TestGetSport() {
        Assertions.assertEquals(competInd1.getSport().getNom(), "Volley");
    }

    @Test
    public void TestSetNom() {
        competInd1.setNom("Compet BeachVolley");
        Assertions.assertEquals(competInd1.getNom(), "Compet BeachVolley");

    }

    @Test
    public void TestGetParticipant() {
        Assertions.assertEquals(liParticipantCheck.equals(competInd1.getParticipant()), true);
    }

    @Test
    public void TestParticipantPresent() {
        Assertions.assertEquals(competInd1.participantPresent(athlete1), true);
    }

    @Test
    public void TestSupParticipant() {
        competInd1.suppParticipant(athlete2);
        Assertions.assertEquals(competInd1.getParticipant().contains(athlete2), false);
    }

    @Disabled
    public void TestClassement() {
        Assertions.assertEquals(competInd1.classement().equals("t"), true);
    }

}
    
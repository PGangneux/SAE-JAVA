package fr.univ_orleans.iut45.mud;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.Pays;
import fr.univ_orleans.iut45.mud.items.Sport;

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

    @Test
    public void testGetListAthlete(){
        List<Athlete> res = Arrays.asList(athlete1,athlete2);
        Assertions.assertEquals(equipeTest1.getLiAthlete(), res);
    }

    @Test
    public void testAjouteAthlete() {
        equipeTest1.ajouteAthlete(athlete1);
        equipeTest1.ajouteAthlete(athlete2);
        equipeTest1.ajouteAthlete(athlete3);
        List<Athlete> res = Arrays.asList(athlete1,athlete2,athlete3);
        Assertions.assertEquals(equipeTest1.getLiAthlete(), res);
    }

    @Test
    public void testSupAthlete() {
        equipeTest1.supAthlete(athlete3);
        List<Athlete> res = Arrays.asList(athlete1,athlete2);
        Assertions.assertEquals(equipeTest1.getLiAthlete(), res);
    }

    @Test
    public void testSetSport(){
        Sport sportTest = new Sport("SportTest");
        equipeTest1.setSport(sportTest);
        Assertions.assertEquals(equipeTest1.getSport(), sportTest);
    }

    @Test
    public void testSetPays(){
        Pays paysTest = new Pays("PaysTest");
        equipeTest1.setPays(paysTest);
        Assertions.assertEquals(equipeTest1.getPays(), paysTest);
    }

    @Test
    public void testEquals(){
        Equipe equipeTest2 = new Equipe("Equipe2", "F", paysE1, vollley);
        Equipe equipeTest3 = equipeTest1;
        Equipe equipeTest4 = new Equipe("Equipe1", "M", paysE1, vollley);
        Assertions.assertEquals(equipeTest1.equals(equipeTest1), true);
        Assertions.assertEquals(equipeTest1.equals(equipeTest2), false);
        Assertions.assertEquals(equipeTest1.equals(equipeTest3), true);
        Assertions.assertEquals(equipeTest1.equals(equipeTest4), true);
    }

    @Disabled
    public void testHashCode(){
        Equipe equipeTest2 = new Equipe("Equipe2", "F", paysE1, vollley);
        Assertions.assertEquals(equipeTest1.hashCode(), 1106881504);
        Assertions.assertEquals(equipeTest2.hashCode(), 1106904568);
    }
}

package fr.univ_orleans.iut45.mud;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestImportData { 
    private static String chemin;
    private static ImportData donnees;
    private static Set<Pays> ensPays;
    private static Set<Sport> ensSports;
    private static List<Athlete> liAthletes;

    @BeforeAll
    public static void setUp() {
        chemin = "./src/test/java/fr/univ_orleans/iut45/mud/donnees.csv";
        donnees = new ImportData(chemin);
        ImportData dataerrorTest = new ImportData("");
        ensSports = donnees.getEnsSports();
        liAthletes = donnees.getListAthletes();
    }
    @Test
    public void testGetPays() {
        ensPays = donnees.getEnsPays();

    }
    @Test
    public void testGetEnsSport() {
        ensSports = donnees.getEnsSports();

    }
    @Test
    public void testGetListAthlete() {
        liAthletes = donnees.getListAthletes();
        //a compléter suivant l'implémentation des classe athletes,Pays et d'autres
    }
}

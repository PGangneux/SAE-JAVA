package fr.univ_orleans.iut45.mud;



import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.RequestingUserName;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.ImportData;
import fr.univ_orleans.iut45.mud.items.Pays;
import fr.univ_orleans.iut45.mud.items.Sport;
import fr.univ_orleans.iut45.mud.JDBC.*;

public class TestJDBC {
    private static String chemin;
    private static ImportData donnees;
    private static Set<Pays> ensPays;
    private static Set<Sport> ensSports;
    private static List<Athlete> liAthletes;
    private static Connexion laConnexion;
    private static Requetes requetes;

    @BeforeAll
    public static void setUp() throws ClassNotFoundException, SQLException {
        chemin = "./src/test/java/fr/univ_orleans/iut45/mud/data/donnees.csv";
        String server = new String("192.168.202.208");
        String baseName = new String("SAE");
        String user = new String("admin");
        String password = new String("admin");
        donnees = new ImportData(chemin);
        ensSports = donnees.getEnsSports();
        liAthletes = donnees.getListAthletes();
        laConnexion = new Connexion();
        laConnexion.connecter(server, baseName, user, password);
    }

    @Test
    public void testConnection() {
        Assertions.assertEquals(true, laConnexion.isConnecte());
    }
}

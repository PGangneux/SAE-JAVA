package fr.univ_orleans.iut45.mud;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
        String server = new String("localhost");
        String baseName = new String("SAE");
        String user = new String("nathan");
        String password = new String("local");
        donnees = new ImportData(chemin);
        ensSports = donnees.getEnsSports();
        liAthletes = donnees.getListAthletes();
        laConnexion = new Connexion();
        laConnexion.connecter(server, baseName, user, password);
        System.out.println("Status de la connexion avec la BD : "+laConnexion.isConnecte());
    }

    @Disabled // provoque des conflits ==> unsuported classreader class file version major 63, mais la JDBC fonctionne
    public void testConnection() throws SQLException {
        Assertions.assertEquals(true, laConnexion.isConnecte());
        laConnexion.close();
    }
}

package fr.univ_orleans.iut45.mud.app;

import java.sql.SQLException;

import fr.univ_orleans.iut45.mud.JDBC.*;

public class App {
    private Connexion loggingConnexion;
    private Connexion jeuxConnexion;
    private Requetes logQueryAPI;
    private Requetes jeuxQueryAPI;
    private String statusCompte;
    public final static String ADMINISTRATEUR = "administrateur";
    public final static String JOURNALIST = "journalist";
    public final static String ORGANISATEUR = "organisateur";
    
    public void initLoggingConnexion() throws SQLException, ClassNotFoundException {
        String server = "192.168.202.208";
        String baseName = "SAEACCOUNT";
        String user = "applogin";
        String password = "DoNotShare";
        this.loggingConnexion = new Connexion();
        this.loggingConnexion.connecter(server, baseName, user, password);
        this.logQueryAPI = new Requetes(this.loggingConnexion);
    }

    public void initJeuxDBConnexion(String roleUser, String rolePassword) throws SQLException, ClassNotFoundException {
        String server = "192.168.202.208";
        String baseName = "SAEACCOUNT";
        String user = roleUser;
        String password = rolePassword;
        this.jeuxConnexion = new Connexion();
        this.jeuxConnexion.connecter(server, baseName, user, password);
        this.jeuxQueryAPI = new Requetes(jeuxConnexion);
    }

    public App() throws ClassNotFoundException, SQLException  {
        initLoggingConnexion();
    }

}

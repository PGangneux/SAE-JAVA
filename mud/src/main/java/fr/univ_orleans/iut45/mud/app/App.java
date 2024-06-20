package fr.univ_orleans.iut45.mud.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import fr.univ_orleans.iut45.mud.JDBC.*;
import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.Pays;
import fr.univ_orleans.iut45.mud.items.Sport;

public class App {
    private Connexion loggingConnexion;
    private Connexion jeuxConnexion;
    private RequeteLogAPI logQueryAPI;
    private Requetes jeuxQueryAPI;
    private String statusCompte;
    private Set<Sport> ensSport;
    private Set<Pays> ensPays;
    private List<Athlete> liAthletes;
    private Set<CompetCoop> ensCompetitionsCoop;
    private Set<CompetInd> ensCompetitionsInd;
    private List<Equipe> liEquipes;
    public final static String ADMINISTRATEUR = "administrateur";
    public final static String JOURNALIST = "journalist";
    public final static String ORGANISATEUR = "organisateur";
    
    private void initLoggingConnexion() throws SQLException, ClassNotFoundException {
        String server = "192.168.62.208";
        String baseName = "SAEACCOUNT";
        String user = "applogin";
        String password = "applicationPrivateLoginKey";
        this.loggingConnexion = new Connexion();
        this.loggingConnexion.connecter(server, baseName, user, password);
        this.logQueryAPI = new RequeteLogAPI(this.loggingConnexion);
    }

    public void initJeuxDBConnexion(String roleUser, String rolePassword) throws SQLException, ClassNotFoundException {
        String server = "192.168.62.208";
        String baseName = "SAE";
        String user = roleUser;
        String password = rolePassword;
        this.jeuxConnexion = new Connexion();
        this.jeuxConnexion.connecter(server, baseName, user, password);
        this.jeuxQueryAPI = new Requetes(jeuxConnexion);
    }

    public void initModelAttribut() {
        // this.ensSport = this.jeuxQueryAPI. //ajouter méthode correspondante
        // this.ensPays = this.jeuxQueryAPI. //ajouter méthode correspondante
        // this.liAthletes = this.jeuxQueryAPI. //ajouter méthode correspondante
        // this.ensCompetitionsCoop = this.jeuxQueryAPI. //ajouter méthode correspondante
        // this.ensCompetitionsInd = this.jeuxQueryAPI. //ajouter méthode correspondante
        // this.liEquipes = this.jeuxQueryAPI. //ajouter méthode correspondante
    }

    public App() throws ClassNotFoundException, SQLException  {
        //initLoggingConnexion();
    }

    public boolean getConnexion(String username, String password) throws SQLException, ClassNotFoundException {
        if (this.logQueryAPI.checkUser(username, password)) {
            String appProvilege = this.logQueryAPI.getUserPrivilege(username);
            this.initJeuxDBConnexion(appProvilege, "applicationPrivateLoginKey");
            this.statusCompte = appProvilege;
            return true;
        }
        return false;
    }

    public void setStatusCompte(String status) {
        this.statusCompte = status;
    }

    public String getStatusCompte() {
        return this.statusCompte;
    }

    /**
     * Retourne l'ensemble des sports importés.
     *
     * @return un ensemble de sports.
     */
    public Set<Sport> getEnsSports(){
        return this.ensSport;
    }


     /**
     * Retourne l'ensemble des pays importés.
     *
     * @return un ensemble de pays.
     */
    public Set<Pays> getEnsPays(){
        return this.ensPays;
    }


    /**
     * Retourne la liste des athlètes importés.
     *
     * @return une liste d'athlètes.
     */
    public List<Athlete> getListAthletes(){
        return this.liAthletes;
    }


    /**
     * Retourne l'ensemble des compétitions coopérative importées.
     *
     * @return un ensemble de CompetCoop.
     */
    public Set<CompetCoop> getEnsCompetitionsCoop() {
        return this.ensCompetitionsCoop;
    }

    /**
     * Retourne l'ensemble des compétitions individuel importées.
     *
     * @return un ensemble de CompetInd.
     */
    public Set<CompetInd> getEnsCompetitionsInd() {
        return this.ensCompetitionsInd;
    }

    /**
     * Retourne la liste des équipes importées.
     *
     * @return une liste d'équipes.
     */
    public List<Equipe> getListEquipes(){
        return this.liEquipes;
    }
}

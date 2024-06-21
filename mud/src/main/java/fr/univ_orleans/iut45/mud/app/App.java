package fr.univ_orleans.iut45.mud.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.ldap.HasControls;

import fr.univ_orleans.iut45.mud.JDBC.*;
import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.epreuve.Epreuve;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoop;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveInd;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.ImportData;
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
    private static Set<CompetCoop> ensCompetitionsCoop;
    private static Set<CompetInd> ensCompetitionsInd;
    private List<Equipe> liEquipes;
    public static boolean alwaysConnectTrue; //Permet de passer la phase de connection sans init la BD
    public final static String ADMINISTRATEUR = "administrateur";
    public final static String JOURNALIST = "journalist";
    public final static String ORGANISATEUR = "organisateur";
    
    private void initLoggingConnexion() throws SQLException, ClassNotFoundException {
        if (alwaysConnectTrue) return ;
        else {
            String server = "localhost";
            String baseName = "SAEACCOUNT";
            String user = "applogin";
            String password = "applicationPrivateLoginKey";
            this.loggingConnexion = new Connexion();
            this.loggingConnexion.connecter(server, baseName, user, password);
            this.logQueryAPI = new RequeteLogAPI(this.loggingConnexion);
        }   
    }

    // this.liAthletes = this.jeuxQueryAPI. //ajouter méthode correspondante
    // this.liEquipes = this.jeuxQueryAPI. //ajouter méthode correspondante
    private void initEnsSport() {
        for (CompetInd compet: ensCompetitionsInd) {
            this.ensSport.add(compet.getSport());
        }
        for (CompetCoop compet: ensCompetitionsCoop) {
            for (Equipe equipe: compet.getParticipant()) {
                this.ensSport.add(equipe.getSport());
            }
        }
    }

    private void initEnsPays() {
        for (CompetInd compet: ensCompetitionsInd) {
            for (Athlete ath: compet.getParticipant()) {
                this.ensPays.add(ath.getPays());
            }
        }
        for (CompetCoop compet: ensCompetitionsCoop) {
            for (Equipe equipe: compet.getParticipant()) {
                this.ensPays.add(equipe.getPays());
            }
        }
    }

    private void initListAthlete() {
        for (CompetInd compet: ensCompetitionsInd) {
            for (Athlete ath: compet.getParticipant()) {
                this.liAthletes.add(ath);
            }
        }
        for (CompetCoop compet: ensCompetitionsCoop) {
            for (Equipe equipe: compet.getParticipant()) {
                for (Athlete ath: equipe.getLiAthlete()) {
                    this.liAthletes.add(ath);
                }
            }
        }
    }

    private void initListEquipe() {
        for (CompetCoop compet: ensCompetitionsCoop) {
            for (Equipe equipe: compet.getParticipant()) {
                this.liEquipes.add(equipe);
            }
        }
    }

    private void initEnsCompetitionsCoop() throws SQLException {
        Set<CompetCoop> competitions = this.jeuxQueryAPI.getEnsembleCompetCoop();
        for (CompetCoop compet: competitions) {
            ensCompetitionsCoop.add(compet);
        }
    }

    private void initEnsCompetitionsInd() throws SQLException {
        Set<CompetInd> competitions = this.jeuxQueryAPI.getEnsembleCompetInd();
        for (CompetInd compet: competitions) {
            ensCompetitionsInd.add(compet);
        }
    }

    public void initJeuxDBConnexion(String roleUser, String rolePassword) throws SQLException, ClassNotFoundException {
        if (alwaysConnectTrue) return ;
        String server = "localhost";
        String baseName = "SAE";
        String user = roleUser;
        String password = rolePassword;
        this.jeuxConnexion = new Connexion();
        this.jeuxConnexion.connecter(server, baseName, user, password);
        this.jeuxQueryAPI = new Requetes(jeuxConnexion);
        initModelAttribut();
    }

    public void initModelAttribut() throws SQLException {
        initEnsCompetitionsCoop();
        initEnsCompetitionsInd();
        initEnsPays();
        initEnsSport();
        initListAthlete();
        initListEquipe();
    }

    public void importCompetCoopFromCSV(Set<CompetCoop> ensCompetCoops) {
        for (CompetCoop compet: ensCompetCoops) {
            ensCompetitionsCoop.add(compet);
        }
    }
    
    public void  importCompetIndFromCSV(Set<CompetInd> ensCompetInd) {
        for (CompetInd compet: ensCompetInd) {
            ensCompetitionsInd.add(compet);
        }
    }
    
    public void  importSportFromCSV(Set<Sport> sports) {
        for (Sport sp: sports) {
            this.ensSport.add(sp);
        }
    }
    
    public void  importPaysFromCSV(Set<Pays> lesPays) {
        for (Pays pays: lesPays) {
            this.ensPays.add(pays);
        }
    }
    
    public void  importAthleteFromCSV(List<Athlete> lesAthletes) {
        for (Athlete ath: lesAthletes) {
            this.liAthletes.add(ath);
        }
    }
    
    public void  importEquipeFromCSV(List<Equipe> lesEquipes) {
        for (Equipe equipe: lesEquipes) {
            this.liEquipes.add(equipe);
        }
    }

    public void importDataFromCSV(String path) {
        // path = "./src/main/java/fr/univ_orleans/iut45/mud/donnees.csv";
        ImportData data = new ImportData(path);
        this.importCompetCoopFromCSV(data.getEnsCompetitionsCoop());
        this.importCompetIndFromCSV(data.getEnsCompetitionsInd());
        this.importSportFromCSV(data.getEnsSports());
        this.importPaysFromCSV(data.getEnsPays());
        this.importAthleteFromCSV(data.getListAthletes());
        this.importEquipeFromCSV(data.getListEquipes());
        //Partie ou il faut ajouter les données dans la BD
    }

    public void insertDataModelToDB() throws SQLException {
        for (Sport sp: this.ensSport) {
            this.jeuxQueryAPI.ajouterSport(sp);
        }
        for (Pays pays: this.ensPays) {
            this.jeuxQueryAPI.ajouterPays(pays);
        }
        for (Athlete ath: this.liAthletes) {
            this.jeuxQueryAPI.ajouterAthlete(ath);
        }
        // for (CompetInd compet: ensCompetitionsInd) {
        //     this.jeuxQueryAPI.ajouterCompetition(compet);
        //     for (Athlete ath: new HashSet<>(compet.getParticipant()) ) {
        //         this.jeuxQueryAPI.ajouterParticipation(compet,ath);
        //     }
        //     for (EpreuveInd ep: compet.getLiEpreuves()) {
        //         this.jeuxQueryAPI.ajouterEpreuve(ep,compet);
        //     } 
        // }
        // for (CompetCoop compet: ensCompetitionsCoop) {
        //     this.jeuxQueryAPI.ajouterCompetition(compet);
        //     for (Equipe eq: compet.getParticipant()) {
        //         this.jeuxQueryAPI.ajouterEquipe(eq);
        //         for (Athlete ath: eq.getLiAthlete()) {
        //             this.jeuxQueryAPI.ajouterLienAthleteEquipe(eq, ath);
        //         }
        //     }
        //     for (EpreuveCoop ep: compet.getLiEpreuves()) {
        //         this.jeuxQueryAPI.ajouterEpreuve(ep,compet);
        //     } 
        // }
    }

    public void dataBaseInit() throws SQLException {
        insertDataModelToDB();
    }

    public App() throws ClassNotFoundException, SQLException  {
        if (alwaysConnectTrue) this.statusCompte = ADMINISTRATEUR;
        ensCompetitionsCoop = new HashSet<>();
        ensCompetitionsInd = new HashSet<>();
        this.ensPays = new HashSet<>();
        this.ensSport = new HashSet<>();
        this.liAthletes = new ArrayList<>();
        this.liEquipes = new ArrayList<>();
        initLoggingConnexion();
        importDataFromCSV("./src/main/java/fr/univ_orleans/iut45/mud/data/donnees.csv");
        // for(Pays p: this.ensPays) System.out.println(p.getNom());
        System.out.println(this.ensPays.size());
    }

    public boolean getConnexion(String username, String password) throws SQLException, ClassNotFoundException {
        if (alwaysConnectTrue) return true;
        else {
            try {
                if (this.logQueryAPI.checkUser(username, password)) {
                    String appProvilege = this.logQueryAPI.getUserPrivilege(username);
                    this.initJeuxDBConnexion(appProvilege, "applicationPrivateLoginKey");
                    this.statusCompte = appProvilege;
                    return true;
                }
                return false;
            } catch (Exception e ) {
                System.out.println(e.getMessage());
                throw new SQLException("compte inexistant");
            } 
        }          
    }
    
    public boolean closeDBConnection() throws SQLException {
        if (this.jeuxConnexion.isConnecte()) {
            this.jeuxConnexion.close();
            return !(this.jeuxConnexion.isConnecte());
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
        return ensCompetitionsCoop;
    }

    /**
     * Retourne l'ensemble des compétitions individuel importées.
     *
     * @return un ensemble de CompetInd.
     */
    public Set<CompetInd> getEnsCompetitionsInd() {
        return ensCompetitionsInd;
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

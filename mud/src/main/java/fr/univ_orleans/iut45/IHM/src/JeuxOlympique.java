package fr.univ_orleans.iut45.IHM.src;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import fr.univ_orleans.iut45.mud.Sport;
import fr.univ_orleans.iut45.mud.Pays;
import fr.univ_orleans.iut45.mud.Athlete;
import fr.univ_orleans.iut45.mud.CompetCoop;
import fr.univ_orleans.iut45.mud.CompetInd;
import fr.univ_orleans.iut45.mud.Equipe;
import fr.univ_orleans.iut45.mud.ImportData;


public class JeuxOlympique extends Application{
    private Controleur controleur;
    private Scene scene;
    private Stage stage;
    
    private Set<Sport> ensSport;
    private Set<Pays> ensPays;
    private List<Athlete> liAthletes;
    private Set<CompetCoop> ensCompetitionsCoop;
    private Set<CompetInd> ensCompetitionsInd;
    private List<Equipe> liEquipes;




   @Override
    public void init() throws IOException{
        this.controleur = new Controleur(this);
        this.scene = new Scene(new Pane(), 400, 300);
        ImportData data = new ImportData("mud/src/main/java/fr/univ_orleans/iut45/mud/donnees.csv");
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setScene(this.scene);
        this.stage.setTitle("Jeux Olympique");
        this.modeConnexion();
        this.stage.show();
    }

    public VBox pageConnexion() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageConnexion.fxml"));
        loader.setController(this.controleur);
        VBox root = loader.load();
        this.stage.setMinWidth(300);
        this.stage.setMinHeight(400);
        return root;
    }

    public BorderPane pageParticipant() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageParticipant.fxml"));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        this.stage.setMaximized(true);
        return root;
    }

    public BorderPane pageCompetition() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetition.fxml"));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        this.stage.setMaximized(true);
        return root;
    }

    public BorderPane pageCompetitionClassement() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetitionClassement.fxml"));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        this.stage.setMaximized(true);
        return root;
    }

    public BorderPane pageCompetitionLiEp() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetitionListeEp.fxml"));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        this.stage.setMaximized(true);
        return root;
    }

    public BorderPane pagePays() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PagePays.fxml"));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        this.stage.setMaximized(true);
        return root;
    }

    public void popUpParamètres(){
        
    }




    public void modeConnexion() throws IOException{
        this.scene.setRoot(this.pageConnexion());
    }

    public void modeParticipant() throws IOException{
        this.scene.setRoot(this.pageParticipant());
    }

    public void modeCompetition() throws IOException{
        this.scene.setRoot(this.pageCompetition());
    }

    public void modeCompetitionLiEp() throws IOException{
        this.scene.setRoot(this.pageCompetitionLiEp());
    }

    public void modeCompetitionClassement() throws IOException{
        this.scene.setRoot(this.pageCompetitionClassement());
    }

    public void modePays() throws IOException{
        this.scene.setRoot(this.pagePays());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

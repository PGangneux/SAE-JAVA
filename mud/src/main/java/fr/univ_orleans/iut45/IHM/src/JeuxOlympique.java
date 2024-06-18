package fr.univ_orleans.iut45.IHM.src;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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

    
    private VBox leftVboxCompet;




   @Override
    public void init() throws IOException{
        this.controleur = new Controleur(this);
        this.scene = new Scene(new Pane(), 400, 300);
        ImportData data = new ImportData("/home/iut45/Etudiants/o22300799/Cour/SAE/SAE-JAVA/mud/src/main/java/fr/univ_orleans/iut45/mud/donnees.csv");
        this.ensCompetitionsCoop = data.getEnsCompetitionsCoop();
        this.ensCompetitionsInd = data.getEnsCompetitionsInd();
        
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
        this.leftVboxCompet = (VBox) root.lookup("#leftVboxCompet");
        Button homme = new Button("Homme");
        VBox.setMargin(homme, new Insets(10, 0, 0, 10));

        this.leftVboxCompet.getChildren().add(homme);

        ToggleGroup groupRadioBCompetHomme = new ToggleGroup();
        for (CompetCoop compet: this.ensCompetitionsCoop){
            if (compet.getSexe().equals("M")){
                RadioButton r = new RadioButton(compet.getNom());
                r.setToggleGroup(groupRadioBCompetHomme);
                this.leftVboxCompet.getChildren().add(r);
            }
        } 
        for (CompetInd compet : this.ensCompetitionsInd){
            if (compet.getSexe().equals("M")){
                RadioButton r = new RadioButton(compet.getNom());
                r.setToggleGroup(groupRadioBCompetHomme);
                this.leftVboxCompet.getChildren().add(r);
            }
        }
        

        Button femme = new Button("Femme");
        
        this.leftVboxCompet.getChildren().add(femme);
        VBox.setMargin(femme, new Insets(10, 0, 0, 10));


        ToggleGroup groupRadioBCompetFemme = new ToggleGroup();
        for (CompetCoop compet: this.ensCompetitionsCoop){
            if (compet.getSexe().equals("F")){
                RadioButton r = new RadioButton(compet.getNom());
                r.setToggleGroup(groupRadioBCompetFemme);
                r.setVisible(false);
                this.leftVboxCompet.getChildren().add(r);
            }
        } 
        for (CompetInd compet : this.ensCompetitionsInd){
            if (compet.getSexe().equals("F")){
                RadioButton r = new RadioButton(compet.getNom());
                r.setToggleGroup(groupRadioBCompetFemme);
                r.setVisible(false);
                this.leftVboxCompet.getChildren().add(r);
            }
        }
        homme.setOnAction(new ControleurRadioButtonCompetition(this, groupRadioBCompetHomme, groupRadioBCompetFemme));
        femme.setOnAction(new ControleurRadioButtonCompetition(this,groupRadioBCompetFemme, groupRadioBCompetHomme));
        
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



    public Set<CompetCoop> getCompetCoop(){
        return this.ensCompetitionsCoop;
    }

    public Set<CompetInd> getCompetInd(){
        return this.ensCompetitionsInd;
    }

    




    public static void main(String[] args) {
        launch(args);
    }
    
}

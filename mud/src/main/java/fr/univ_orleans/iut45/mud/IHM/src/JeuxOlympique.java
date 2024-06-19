package fr.univ_orleans.iut45.mud.IHM.src;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import fr.univ_orleans.iut45.mud.items.*;
import fr.univ_orleans.iut45.mud.competition.*;
import fr.univ_orleans.iut45.mud.IHM.src.controlleur.*;;


public class JeuxOlympique extends Application{
    private Controleur controleur;
    private Scene scene;
    private Stage stage;
    private ImportData model;

    /**
     * Si true le thème est clair sinon le thème est sombre
     */
    private boolean theme;
    

    
    private VBox leftVboxCompet;
    private Button femme;
    private Button homme;
    private ToggleGroup groupRadioBCompetHomme;
    private ToggleGroup groupRadioBCompetFemme;
    private Label competClassement1;
    private Label competClassement2;
    private Label competClassement3;

    // private MediaPlayer son;

    private GridPane classementPays;
    private GridPane recherchePays;





        
    public Stage getStage(){
        return this.stage;
    }
    

    public ToggleGroup getGroupRadioBCompetHomme() {
        return groupRadioBCompetHomme;
    }

    public ToggleGroup getGroupRadioBCompetFemme() {
        return groupRadioBCompetFemme;
    }

    @Override
    public void init() throws IOException{
        this.theme = true;
        this.controleur = new Controleur(this,model);
        this.scene = new Scene(new Pane(), 400, 300);
        ImportData data = new ImportData("./src/main/java/fr/univ_orleans/iut45/mud/data/donnees.csv");
        this.model = data;
        // Ajouter import pour media pour le son et un attribut
        // this.son = new MediaPlayer(new Media("acces fichier son"));
        
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
        loader.setControllerFactory(c -> new Controleur(this,model)); //A mettre a la place de tout les loader.setControler(this.controleur)
        loader.setController(this.controleur);
        VBox root = loader.load();
        this.stage.setWidth(300);
        this.stage.setHeight(400);
        return root;
    }

    public BorderPane pageParticipant() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageParticipant.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        return root;
    }

    public BorderPane pageCompetition() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetition.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.leftVboxCompet = (VBox) root.lookup("#leftVboxCompet");
        this.homme = (Button) root.lookup("#homme");
        this.femme = (Button) root.lookup("#femme");
        this.competClassement1 = (Label) root.lookup("#premier");
        this.competClassement2 = (Label) root.lookup("#deuxieme");
        this.competClassement3 = (Label) root.lookup("#troisieme");



        this.leftVboxCompet.getChildren().remove(1);   
        this.groupRadioBCompetHomme = new ToggleGroup();

        ControlleurRadioButtonCompetition controlleurRadioBCompet = new ControlleurRadioButtonCompetition(this, this.model);
        for (CompetCoop compet: this.model.getEnsCompetitionsCoop()){
            if (compet.getSexe().equals("M")){
                RadioButton r = new RadioButton(compet.getNom());
                r.setOnAction(controlleurRadioBCompet);
                r.setToggleGroup(this.groupRadioBCompetHomme);
                this.leftVboxCompet.getChildren().add(r);
            }
        } 
        for (CompetInd compet : this.model.getEnsCompetitionsInd()){
            if (compet.getSexe().equals("M")){
                RadioButton r = new RadioButton(compet.getNom());
                r.setOnAction(controlleurRadioBCompet);
                r.setToggleGroup(this.groupRadioBCompetHomme);
                this.leftVboxCompet.getChildren().add(r);
            }
        }
        
        
        
        this.leftVboxCompet.getChildren().add(this.femme);  
        this.groupRadioBCompetFemme = new ToggleGroup();
        for (CompetCoop compet: this.model.getEnsCompetitionsCoop()){
            if (compet.getSexe().equals("F")){
                RadioButton r = new RadioButton(compet.getNom());
                r.setOnAction(controlleurRadioBCompet);
                r.setToggleGroup(this.groupRadioBCompetFemme);
                r.setVisible(false);
                this.leftVboxCompet.getChildren().add(r);
            }
        } 
        for (CompetInd compet : this.model.getEnsCompetitionsInd()){
            if (compet.getSexe().equals("F")){
                RadioButton r = new RadioButton(compet.getNom());
                r.setOnAction(controlleurRadioBCompet);
                r.setToggleGroup(this.groupRadioBCompetFemme);
                r.setVisible(false);
                this.leftVboxCompet.getChildren().add(r);
            }
        }
        
        

        
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        return root;
    }


    public void majCompet(String premier, String seccond, String troisieme){
        this.competClassement1.setText(premier);
        this.competClassement2.setText(seccond);
        this.competClassement3.setText(troisieme);
    }

    public BorderPane pageCompetitionClassement() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetitionClassement.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        return root;
    }

    public BorderPane pageCompetitionLiEp() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetitionListeEp.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        return root;
    }

    public BorderPane pagePays() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PagePays.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.classementPays = (GridPane) root.lookup("#classementPays");
        List<Pays> classementTotal = Pays.classementPaysMedaille(this.model.getEnsPays());
        int i = 0;
        for (Pays pays: classementTotal){
            i++;
            this.classementPays.add(new Label(String.valueOf(i)), 0, i);
            this.classementPays.add(new Label(pays.getNom()), 1, i);
            this.classementPays.add(new Label(String.valueOf(pays.getCompteurMedaille())), 2, i);
            this.classementPays.add(new Label(String.valueOf(pays.getCompteurMedailleOr())), 5, i);
        }

        this.recherchePays = (GridPane) root.lookup("#recherchePays");
        // à voir 

        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        return root;
    }

    public BorderPane pageParamAffichage() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageParamAffichage.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(600);
        this.stage.setMinHeight(450);
        return root;
    }

    public BorderPane pageParamAudio() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageParamAudio.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(600);
        this.stage.setMinHeight(450);
        return root;
    }

    public BorderPane pageParamPref() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageParamPref.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(600);
        this.stage.setMinHeight(450);
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

    public void themeSombre(){
        this.theme = false;
        this.scene.getStylesheets().add("https://raw.githubusercontent.com/antoniopelusi/JavaFX-Dark-Theme/main/style.css");
    }

    public void themeClair(){
        this.theme = true;
        this.scene.getStylesheets().remove("https://raw.githubusercontent.com/antoniopelusi/JavaFX-Dark-Theme/main/style.css"); 
    }




    // public Set<CompetCoop> getCompetCoop(){
    //     return this.model.getEnsCompetitionsCoop();
    // }

    // public Set<CompetInd> getCompetInd(){
    //     return this.model.getEnsCompetitionsInd();
    // }


    
    public void modeParamAffichage() throws IOException{
        this.scene.setRoot(this.pageParamAffichage());
        RadioButton radioClair = (RadioButton)this.scene.lookup("#radioClair");
        RadioButton radioSombre = (RadioButton)this.scene.lookup("#radioSombre");
        if (this.theme == true){
            radioSombre.setSelected(false);
            radioClair.setSelected(true);
        } else {
            radioClair.setSelected(false);
            radioSombre.setSelected(true);
        }
    }

    public void modeParamAudio() throws IOException{
        this.scene.setRoot(this.pageParamAudio());
    }

    public void modeParamPref() throws IOException{
        this.scene.setRoot(this.pageParamPref());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
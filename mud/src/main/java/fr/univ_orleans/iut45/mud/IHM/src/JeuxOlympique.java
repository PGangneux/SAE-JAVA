package fr.univ_orleans.iut45.mud.IHM.src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.univ_orleans.iut45.mud.IHM.src.controlleur.*;
import fr.univ_orleans.iut45.mud.competition.*;
import fr.univ_orleans.iut45.mud.epreuve.Epreuve;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoop;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveInd;
import fr.univ_orleans.iut45.mud.items.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;;


public class JeuxOlympique extends Application{
    private Controleur controleur;
    private Scene scene;
    private Stage stage;
    private ImportData model;

    
    

    
    private VBox leftVboxCompet;
    private Button femme;
    private Button homme;
    private ToggleGroup groupRadioBCompetHomme;
    private ToggleGroup groupRadioBCompetFemme;
    private Label competClassement1;
    private Label competClassement2;
    private Label competClassement3;

    private ScrollPane classementCompetScrollPane;
    private GridPane classementCompet;

    
    private GridPane classementPays;
    private GridPane recherchePays;
    private TextField textFieldPays;

    private ScrollPane liEpreuve;





    
        

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
        ImportData data = new ImportData("src/main/java/fr/univ_orleans/iut45/mud/data/donnees.csv");
        this.model = data;
        this.controleur = new Controleur(this,model);
        this.scene = new Scene(new Pane(), 400, 300);
        
        
        
        
        
        
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
        System.out.println(this.model);
        loader.setControllerFactory(c -> new Controleur(this,this.model)); //A mettre a la place de tout les loader.setControler(this.controleur)
        loader.setController(this.controleur);
        VBox root = loader.load();
        this.stage.setWidth(300);
        this.stage.setHeight(400);
        return root;
    }

    public BorderPane pageParticipant() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageParticipant.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,this.model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        return root;
    }

    public BorderPane pageCompetition() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetition.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,this.model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.leftVboxCompet = (VBox) root.lookup("#leftVboxCompet");
        this.homme = (Button) root.lookup("#homme");
        this.femme = (Button) root.lookup("#femme");
        this.liEpreuve = (ScrollPane) root.lookup("#liEpreuve");
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


    public void majCompet(String premier, String seccond, String troisieme, CompetCoop compet){
        this.competClassement1.setText(premier);
        this.competClassement2.setText(seccond);
        this.competClassement3.setText(troisieme);
        VBox epreuves = new VBox();
        if(compet.getLiEpreuves().equals(new ArrayList<>())){
            Label label = new Label("Il n'y a pas encore d'épreuve");
            epreuves.getChildren().add(label);
            this.liEpreuve.setContent(epreuves);
        }
        for (EpreuveCoop ep:compet.getLiEpreuves()){
            Label label = new Label(ep.getNom());
            epreuves.getChildren().add(label);
            this.liEpreuve.setContent(epreuves);
        }
        
        
    }

    public void majCompet(String premier, String seccond, String troisieme, CompetInd compet){
        this.competClassement1.setText(premier);
        this.competClassement2.setText(seccond);
        this.competClassement3.setText(troisieme);
        VBox epreuves = new VBox();
        if(compet.getLiEpreuves().equals(new ArrayList<>())){
            Label label = new Label("Il n'y a pas encore d'épreuve");
            epreuves.getChildren().add(label);
            this.liEpreuve.setContent(epreuves);
        }
        for (EpreuveInd ep:compet.getLiEpreuves()){
            Label label = new Label(ep.getNom());
            epreuves.getChildren().add(label);
            this.liEpreuve.setContent(epreuves);
        }
        
    }

    public BorderPane pageCompetitionClassement(CompetCoop compet) throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetitionClassement.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,this.model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        this.classementCompetScrollPane =  (ScrollPane) root.lookup("#classementCompet");
        this.classementCompet = (GridPane) classementCompetScrollPane.getContent();
        List<Equipe> classement = compet.classement();
        int i = 1;
        for(Equipe equipe:classement){
            this.classementCompet.add(new Label(String.valueOf(i)),0,i);
            this.classementCompet.add(new Label(equipe.getNom()),1,i);
            this.classementCompet.add(new Label(equipe.getPays().getNom()),2,i);
            i++;
        }
        this.classementCompet.setVgap(20);
        return root;
    }

    public BorderPane pageCompetitionClassement(CompetInd compet) throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PageCompetitionClassement.fxml"));
        loader.setControllerFactory(c -> new Controleur(this,this.model));
        loader.setController(this.controleur);
        BorderPane root = loader.load();
        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        this.classementCompetScrollPane =  (ScrollPane) root.lookup("#classementCompet");
        this.classementCompet = (GridPane) classementCompetScrollPane.getContent();

        List<Athlete> classement = compet.classement();
        int i = 1;
        for(Athlete athlete:classement){
            this.classementCompet.add(new Label(String.valueOf(i)),0,i);
            this.classementCompet.add(new Label(athlete.getNom()+ " "+athlete.getPrenom()),1,i);
            this.classementCompet.add(new Label(athlete.getPays().getNom()),2,i);
            i++;
        }
        this.classementCompet.setVgap(20);
        return root;
    }

    

    public BorderPane pagePays() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PagePays.fxml"));
        
        loader.setControllerFactory(c -> new Controleur(this,this.model));
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
            this.classementPays.add(new Label(String.valueOf(pays.getCompteurMedailleBronze())), 3, i);
            this.classementPays.add(new Label(String.valueOf(pays.getCompteurMedailleArgent())), 4, i);
            this.classementPays.add(new Label(String.valueOf(pays.getCompteurMedailleOr())), 5, i);
        }

        this.recherchePays = (GridPane) root.lookup("#recherchePays");
        this.textFieldPays = (TextField) root.lookup("#textFieldPays");
        

        this.stage.setMinWidth(890);
        this.stage.setMinHeight(500);
        return root;
    }

    public void majPays(Pays pays){
        try{
            this.modePays();
        }
        catch(IOException e){}
        Image image = new Image(getClass().getResource("/fr/univ_orleans/iut45/mud/IHM/img/flags/" + pays.getNom() + ".png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50); 
        imageView.setFitHeight(35); 
        imageView.setPreserveRatio(true);
        GridPane.setMargin(imageView, new Insets(0,0,0,10));
        Label nom = new Label(pays.getNom());

                    
        Label nbMedaille = new Label(String.valueOf(pays.getCompteurMedaille()));
        Label nbMedailleOr = new Label(String.valueOf(pays.getCompteurMedailleOr()));
        Label nbMedailleArgent = new Label(String.valueOf(pays.getCompteurMedailleArgent()));
        Label nbMedailleBronze = new Label(String.valueOf(pays.getCompteurMedailleBronze()));
        this.recherchePays.add(imageView, 0, 1);
        this.recherchePays.add(nom, 1, 1);
        this.recherchePays.add(nbMedaille, 1, 2);
        this.recherchePays.add(nbMedailleOr, 1, 3);
        this.recherchePays.add(nbMedailleArgent, 1, 4);
        this.recherchePays.add(nbMedailleBronze, 1, 5);

        GridPane.setMargin(nbMedaille, new Insets(0,0,50,25));
        GridPane.setMargin(nbMedailleOr, new Insets(0,0,50,25));
        GridPane.setMargin(nbMedailleArgent, new Insets(0,0,50,25));
        GridPane.setMargin(nbMedailleBronze, new Insets(0,0,50,25));
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


    public void modeCompetitionClassement(CompetCoop compet) throws IOException{
        this.scene.setRoot(this.pageCompetitionClassement(compet));
    }

    public void modeCompetitionClassement(CompetInd compet) throws IOException{
        this.scene.setRoot(this.pageCompetitionClassement(compet));
    }

    public void modePays() throws IOException{
        this.scene.setRoot(this.pagePays());
    }




    // public Set<CompetCoop> getCompetCoop(){
    //     return this.model.getEnsCompetitionsCoop();
    // }

    // public Set<CompetInd> getCompetInd(){
    //     return this.model.getEnsCompetitionsInd();
    // }


    
    public void modeParamAffichage() throws IOException{
        this.scene.setRoot(this.pageParamAffichage());
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

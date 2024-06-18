

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JeuxOlympique extends Application{
    private Controleur controleur;
    private Scene scene;
    private Stage stage;
    




   @Override
    public void init() throws IOException{
        this.controleur = new Controleur(this);
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
